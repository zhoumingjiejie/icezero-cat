//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package github.com.icezerocat.ap;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import github.com.icezerocat.component.common.utils.DateJacksonConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_knowledge_query_log")
public class TKnowledgeQueryLog implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private int logId;
    @TableField
    private int knowledgeId;
    @JsonDeserialize(using = DateJacksonConverter.class)
    @TableField
    private Date queryTime;

    public TKnowledgeQueryLog() {
    }

    public void setLogId(int var1) {
        this.logId = var1;
    }

    public int getLogId() {
        return this.logId;
    }

    public void setKnowledgeId(int var1) {
        this.knowledgeId = var1;
    }

    public int getKnowledgeId() {
        return this.knowledgeId;
    }

    public void setQueryTime(Date var1) {
        this.queryTime = var1;
    }

    public Date getQueryTime() {
        return this.queryTime;
    }
}
