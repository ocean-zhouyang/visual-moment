package com.sjsk.passjava.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import lombok.Builder;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ocean
 * @since 2024-09-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    private String name;

    private Integer age;

    private String address;


}
