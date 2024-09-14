package com.sjsk.passjava.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sjsk.passjava.common.utils.R;
import com.sjsk.passjava.model.Student;
import com.sjsk.passjava.model.Teacher;
import com.sjsk.passjava.service.StudentService;
import com.sjsk.passjava.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Api(tags = "测试接口")
@RestController
@RequestMapping("test")
public class TestController {

    @Value("${es.host}")
    private String esHost;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "测试接口",notes = "测试接口说明")
    @GetMapping("test")
    public Object test() {
        Student one = studentService.lambdaQuery().last("limit 1").one();
        return R.ok().put("student", one);
    }


    @GetMapping("test2")
    public Object test2() {
        Teacher one = teacherService.lambdaQuery().last("limit 1").one();
        return R.ok().put("teacher", one);
    }

    @GetMapping("esTest")
    public Object esTest() {
        String MAPPING_TEMPLATE = "{\"mappings\":{\"properties\":{\"id\":{\"type\":\"long\"},\"resId\":{\"type\":\"keyword\"},\"title\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"}}}}";
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(esHost)));
        CreateIndexRequest request = new CreateIndexRequest("res_image");
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        try {
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    @PostMapping("syncToEs")
    public Object syncToEs() {
        List<Student> esStudentList = studentService.list(Wrappers.<Student>lambdaQuery().last("limit 10000"));
        List<EsStudent> esStudents = new ArrayList<>();
        esStudentList.stream().forEach(student -> {
            EsStudent esStudent = new EsStudent();
            esStudent.setId(student.getId());
            esStudent.setName(student.getName());
            esStudent.setAddress(student.getAddress());
            esStudents.add(esStudent);
        });

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(esHost)));
        esStudents.stream().forEach(esStudent -> {
            IndexRequest request = new IndexRequest("student").id(esStudent.getId().toString());
            request.source(JSON.toJSONString(esStudent), XContentType.JSON);
            try {
                client.index(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return R.ok();
    }


    class EsStudent {
        private Integer id;
        private String name;
        private String address;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
