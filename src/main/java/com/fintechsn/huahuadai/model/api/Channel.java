package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 * id BIGINT(19)
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 登录账号
	 * login_name VARCHAR(255)
	 */
	private String loginName;

	/**
	 * 渠道商名称
	 * name VARCHAR(255)
	 */
	private String name;

	/**
	 * 推广链接
	 * link_url VARCHAR(255)
	 */
	private String linkUrl;


	/**
	 * 每单分成利润比例
	 * proportion VARCHAR(255)
	 */
	private String proportion;

	/**
	 * 登录密码
	 * password VARCHAR(255)
	 */
	private String password;

	/**
	 * 登录token
	 * token VARCHAR(255)
	 */
	private String token;

	/**
	 * 总注册会员数
	 * member_count INT(10)
	 */
	private Integer memberCount;


	/**
	 * 总申请金额
	 * apply_money VARCHAR(255)
	 */
	private String applyMoney;

	/**
	 * 总放款金额
	 * out_money VARCHAR(255)
	 */
	private String outMoney;

	/**
	 * 总分成利润
	 * profit VARCHAR(255)
	 */
	private String profit;

	/**
	 * 状态: 1,正常 2,删除
	 * status INT(10)
	 */
	private Integer status;

	//扣量比例0-100
	private int decrementRate;

	/**
	 * 结算方式:
	 * status VARCHAR(255)
	 */
	private String clearnFrom;
	/**
	 * @备注: 是否可用:0 可用,1 不可用
	 * @字段: is_enable char(1)
	 */
	private String isEnable;

	/**
	 * @备注:删除标记:0:正常,1:删除
	 * @字段:del_flag char(1)
	 */
	private String delFlag;
}
