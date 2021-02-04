package com.github.icezerocat.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:  客户端seata日志表
 * Date: 2021-02-03 11:35:35
 *
 * @author 0.0
 */
@Data
@Entity
@Table(name = "undo_log")
public class UndoLog implements Serializable {

    private static final long serialVersionUID = 6499558031333116175L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "xid")
    private String xid;

    @Column(name = "context")
    private String context;

    @Column(name = "rollback_info")
    private byte[] rollbackInfo;

    @Column(name = "log_status")
    private Long logStatus;

    @Column(name = "log_created")
    private Date logCreated;

    @Column(name = "log_modified")
    private Date logModified;

    @Column(name = "ext")
    private String ext;

}
