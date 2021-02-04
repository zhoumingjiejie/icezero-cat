package com.github.icezerocat.jpa.repository;

import com.github.icezerocat.jpa.entity.UndoLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description: UndoLog
 * CreateDate:  2021/2/3 13:46
 *
 * @author zero
 * @version 1.0
 */
public interface UndoLogRepository extends JpaRepository<UndoLog, Long> {
}
