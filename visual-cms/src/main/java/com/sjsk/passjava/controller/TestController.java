package com.sjsk.passjava.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sjsk.passjava.common.utils.R;
import com.sjsk.passjava.config.WebSocketServer;
import com.sjsk.passjava.model.Student;
import com.sjsk.passjava.model.Teacher;
import com.sjsk.passjava.model.User;
import com.sjsk.passjava.service.StudentService;
import com.sjsk.passjava.service.TeacherService;
import com.sjsk.passjava.service.UserService;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserService userService;

    @Resource
    private WebSocketServer webSocketServer;

    @ApiOperation(value = "测试接口", notes = "测试接口说明")
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
        List<User> esUserList = userService.list(Wrappers.<User>lambdaQuery().last("limit 10000"));

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(esHost)));
        esUserList.stream().forEach(user -> {
            IndexRequest request = new IndexRequest("user").id(user.getUserId().toString());
            request.source(JSON.toJSONString(user), XContentType.JSON);
            try {
                client.index(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return R.ok();
    }


    @ApiOperation(value = "websocket发送消息", notes = "websocket发送消息测试")
    @GetMapping("/push/{userId}")
    public Map pushToWeb(@PathVariable String userId, String message) {
        Map<String,Object> result = new HashMap<>();
        webSocketServer.sendOneMessage(userId, message);
        result.put("code", userId);
        result.put("msg", message);
        return result;
    }

}
