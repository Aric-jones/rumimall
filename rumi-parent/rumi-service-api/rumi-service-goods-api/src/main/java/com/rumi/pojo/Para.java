package com.rumi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/4 16:00
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_para")
@AllArgsConstructor
@NoArgsConstructor
public class Para implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private String name;

    private String options;

    private Integer seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Para para = (Para) o;
        return Objects.equals(id, para.id) && Objects.equals(name, para.name) && Objects.equals(options, para.options) && Objects.equals(seq, para.seq) && Objects.equals(templateId, para.templateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, options, seq, templateId);
    }

    private Integer templateId;


}
