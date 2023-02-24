package shop.mtcoding.jobara.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String detailAddress;
    private String tel;
    private String realName;
    private Integer career;
    private String resumeTitle;
    private String resumeContent;
    private Timestamp createdAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(Integer id, String username, String password, String email,
            String address, String detailAddress, String tel, Integer career) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
        this.tel = tel;
        this.career = career;
    }
}
