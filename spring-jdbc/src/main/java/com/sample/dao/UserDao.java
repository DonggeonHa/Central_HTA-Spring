package com.sample.dao;

import com.sample.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO sample_users "
                   + "(user_id, user_name, user_password, user_email, user_phone) "
                   + "VALUES "
                   + "(?, ?, ?, ?, ?) ";

        jdbcTemplate.update(sql, user.getId(),
                                 user.getName(),
                                 user.getPassword(),
                                 user.getEmail(),
                                 user.getPhone());
    }

    public void updateUser(User user) {
        String sql = "UPDATE sample_users " +
                     "SET " +
                     "  user_name       = ?, " +
                     "  user_password   = ?, " +
                     "  user_email      = ?, " +
                     "  user_phone      = ?, " +
                     "  user_status     = ?, " +
                     "  user_point      = ? " +
                     "WHERE user_id = ? ";

        jdbcTemplate.update(sql, user.getName(),
                                 user.getPassword(),
                                 user.getEmail(),
                                 user.getPhone(),
                                 user.getStatus(),
                                 user.getPoint(),
                                 user.getId());
    }

    public User getUserById(String userId) {
        String sql = "SELECT * " +
                     "FROM sample_users " +
                     "WHERE user_id = ? ";

        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));
                user.setStatus(rs.getString("user_status"));
                user.setPoint(rs.getInt("user_point"));
                user.setCreatedDate(rs.getDate("user_created_date"));

                return user;
            }
        }, userId);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * " +
                     "FROM sample_users ";

        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));
                user.setStatus(rs.getString("user_status"));
                user.setPoint(rs.getInt("user_point"));
                user.setCreatedDate(rs.getDate("user_created_date"));

                return user;
            }
        });

    }
}
