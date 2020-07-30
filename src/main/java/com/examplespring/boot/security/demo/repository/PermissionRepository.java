package com.examplespring.boot.security.demo.repository;

import com.examplespring.boot.security.demo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class PermissionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取权限
     * @param username
     * @return
     */
    public List<String> getCodeList(String username) {
        String sql = "SELECT DISTINCT(`code`) FROM t_permission AS p,t_role_permission AS rp,t_role AS r " +
                "WHERE r.id=rp.role_id AND rp.permission_id=p.id AND r.id IN " +
                "(SELECT r.id FROM t_user AS u,t_role AS r,t_user_role AS ur " +
                "WHERE u.username = ? AND u.id=ur.user_id AND ur.role_id=r.id)";
        List<String> codeList = jdbcTemplate.queryForList(sql, new Object[]{username}, String.class);
        if (CollectionUtils.isEmpty(codeList)) {
            return null;
        }
        return codeList;
    }

}
