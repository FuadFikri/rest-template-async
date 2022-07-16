package id.fikri.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Todo implements Serializable {

    private Integer id;
    private Integer userId;
    private String title;
    private boolean completed;


}
