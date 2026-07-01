-- ============================================
-- 校园互助学习平台 数据库设计
-- ============================================

CREATE DATABASE IF NOT EXISTS campus_qa DEFAULT CHARSET utf8mb4;
USE campus_qa;

-- 1. 用户表
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(20) NOT NULL UNIQUE COMMENT '学号',
    username VARCHAR(50) NOT NULL COMMENT '昵称',
    password VARCHAR(255) NOT NULL COMMENT '加密后的密码',
    avatar VARCHAR(500) DEFAULT '' COMMENT '头像URL',
    bio VARCHAR(200) DEFAULT '' COMMENT '个人简介',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB COMMENT='用户';

-- 2. 关注表
CREATE TABLE follow (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    follower_id BIGINT NOT NULL COMMENT '关注者',
    following_id BIGINT NOT NULL COMMENT '被关注者',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_follow (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES user(id),
    FOREIGN KEY (following_id) REFERENCES user(id)
) ENGINE=InnoDB COMMENT='关注';

-- 3. 收藏表
CREATE TABLE favorite (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    question_id BIGINT NULL COMMENT '收藏的问题',
    resource_id BIGINT NULL COMMENT '收藏的资源',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB COMMENT='收藏';

-- 4. 管理员表
CREATE TABLE admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB COMMENT='管理员';

-- 5. 课程分类表
CREATE TABLE course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '课程名称',
    description VARCHAR(500) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB COMMENT='课程分类';

-- 6. 问题表
CREATE TABLE question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    image_url VARCHAR(500) DEFAULT '',
    status TINYINT DEFAULT 1 COMMENT '1正常 0违规下架',
    like_count INT DEFAULT 0,
    answer_count INT DEFAULT 0,
    is_resolved TINYINT DEFAULT 0 COMMENT '0未解决 1已解决',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
) ENGINE=InnoDB COMMENT='问题';

-- 7. 回答表
CREATE TABLE answer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    is_accepted TINYINT DEFAULT 0 COMMENT '0未采纳 1已采纳',
    like_count INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB COMMENT='回答';

-- 8. 评论表
CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    answer_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content VARCHAR(500) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (answer_id) REFERENCES answer(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB COMMENT='评论';

-- 9. 点赞表
CREATE TABLE question_like (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_like (question_id, user_id),
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB COMMENT='问题点赞';

-- 10. 资源表
CREATE TABLE resource (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    filename VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT DEFAULT 0 COMMENT '字节',
    file_type VARCHAR(50) DEFAULT '',
    download_count INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
) ENGINE=InnoDB COMMENT='资源';

-- 初始数据：插入默认课程和管理员
INSERT INTO course (name, description) VALUES
('Java程序设计', 'Java语言基础与进阶'),
('数据结构', '常用数据结构与算法'),
('数据库原理', 'MySQL数据库设计与应用'),
('计算机网络', 'TCP/IP协议与网络编程'),
('操作系统', '操作系统原理与实践');

INSERT INTO admin (username, password) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5E');
