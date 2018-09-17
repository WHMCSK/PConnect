package cn.bgenius.pconnect.todo.entity;

import javax.persistence.*;

@Table(name = "dm_usr_todoes_change_history")
public class TTodoesChangeHistory {
    @Id
    private Integer id;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}