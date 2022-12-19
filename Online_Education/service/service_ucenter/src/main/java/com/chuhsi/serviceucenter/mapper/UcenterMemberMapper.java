package com.chuhsi.serviceucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuhsi.serviceucenter.entity.UcenterMember;
import io.lettuce.core.dynamic.annotation.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2020-08-11
 */


public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer ucenterMemberService(String day);
}
