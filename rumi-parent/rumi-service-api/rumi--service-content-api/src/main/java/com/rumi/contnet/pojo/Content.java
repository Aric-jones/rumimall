package com.rumi.contnet.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/4 16:00
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_content")
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Serializable{

	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	private Long categoryId;

	private String title;

	private String url;

	private String pic;

	//状态,0无效，1有效
	private String status;


	private Integer sortOrder;




}
