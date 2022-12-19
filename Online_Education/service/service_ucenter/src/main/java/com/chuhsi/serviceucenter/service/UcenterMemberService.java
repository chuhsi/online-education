package com.chuhsi.serviceucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.serviceucenter.entity.UcenterMember;
import com.chuhsi.serviceucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-11
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    UcenterMember getMenberByOperid(String openid);

    Integer ucenterMemberService(String day);
}
