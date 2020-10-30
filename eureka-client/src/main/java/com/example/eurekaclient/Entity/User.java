package com.example.eurekaclient.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@TableName("user_")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id_;
    private String username_;
    private String password_;
    private String nickname_;
}
