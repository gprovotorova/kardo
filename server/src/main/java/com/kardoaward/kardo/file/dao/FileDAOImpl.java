package com.kardoaward.kardo.file.dao;

import com.kardoaward.kardo.file.model.FileInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class FileDAOImpl implements FileDAO {

    private static final String CREATE_FILE = "INSERT INTO files_info(file_name, file_size, file_key, upload_date) VALUES (?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public FileInfo create(final FileInfo file) {
        LocalDate uploadDate = LocalDate.now();
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(x -> {
            PreparedStatement preparedStatement = x.prepareStatement(CREATE_FILE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, file.getName());
            preparedStatement.setLong(2, file.getSize());
            preparedStatement.setString(3, file.getKey());
            preparedStatement.setDate(4, Date.valueOf(uploadDate));
            return preparedStatement;
        }, keyHolder);

        return file.toBuilder()
                .id(keyHolder.getKey().longValue())
                .uploadDate(uploadDate)
                .build();
    }

    private static final String FIND_FILE_BY_ID = "SELECT id, file_name, file_size, file_key, upload_date FROM files_info WHERE id = ?";

    @Override
    public FileInfo findById(Long fileId) {
        return jdbcTemplate.queryForObject(FIND_FILE_BY_ID, rowMapper(), fileId);
    }

    private RowMapper<FileInfo> rowMapper() {
        return (rs, rowNum) -> FileInfo.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("file_name"))
                .size(rs.getLong("file_size"))
                .key(rs.getString("file_key"))
                .path(rs.getString("file_path"))
                .uploadDate(rs.getObject("upload_date", LocalDate.class))
                .build();
    }

    private static final String DELETE_FILE_BY_ID = "DELETE FROM files_info WHERE id = ?";

    @Override
    public void delete(Long fileId) {
        jdbcTemplate.update(DELETE_FILE_BY_ID, fileId);
    }
}

