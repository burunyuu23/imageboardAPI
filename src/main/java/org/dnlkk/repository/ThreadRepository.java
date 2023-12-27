package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.annotations.Param;
import com.dnlkk.repository.annotations.Query;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.model.Thread;

import java.util.List;

@Repository
public interface ThreadRepository extends DnlkkRepository<Integer, Thread> {
    List<Thread> findAll(Pageable pageable);

    @Query(value = """
            WITH RankedMessages AS (
            SELECT thread_table.* ,
            board_table.id AS board_table_id ,
            board_table.name AS board_table_name ,
            board_table.description AS board_table_description ,
            board_table.created_date AS board_table_created_date ,
            board_table.banner AS board_table_banner ,
            message_table.id AS message_table_id ,
            message_table.thread_id AS message_table_thread_id ,
            message_table.body AS message_table_body ,
            message_table.created_date AS message_table_created_date ,
            (COUNT(message_table.id) OVER (PARTITION BY thread_table.id)) as count_all ,
            (COUNT(
                CASE WHEN message_table.created_date >= (CURRENT_TIMESTAMP - INTERVAL '24 hour')
                THEN message_table.id END
                ) OVER (PARTITION BY thread_table.id)
            ) as count_today,
            ROW_NUMBER() OVER (PARTITION BY thread_table.id ORDER BY message_table.id) AS rn 
            FROM thread_table 
            LEFT JOIN board_table ON thread_table.board_id = board_table.id 
            LEFT JOIN message_table ON thread_table.id = message_table.thread_id  
            WHERE thread_table.boardId = :boardId  
            ORDER BY thread_table.id ,board_table.id,message_table.thread_id, message_table_id
            ), 
            UniqueRankedMessages AS (    SELECT *     FROM RankedMessages    WHERE rn = 1       LIMIT 3     OFFSET 0 ) 
            SELECT * FROM(SELECT  DISTINCT ON (id ) * FROM RankedMessages WHERE id IN (SELECT id FROM UniqueRankedMessages)) Z
            ORDER BY id DESC
            """, autoReference = false)
    List<Thread> findByBoardOnlyIgnoredBoard(@Param("boardId") String boardId, Pageable pageable);
    Thread findByBoardOnlyIgnoredBoardRandom(String boardId, Pageable pageable);

    @Query(value = """
            WITH RankedMessages AS (
            SELECT thread_table.* ,
            board_table.id AS board_table_id ,
            board_table.name AS board_table_name ,
            board_table.description AS board_table_description ,
            board_table.created_date AS board_table_created_date ,
            board_table.banner AS board_table_banner ,
            message_table.id AS message_table_id ,
            message_table.thread_id AS message_table_thread_id ,
            message_table.body AS message_table_body ,
            message_table.created_date AS message_table_created_date ,
            (COUNT(message_table.id) OVER (PARTITION BY thread_table.id)) as count_all ,
            (COUNT(
                CASE WHEN message_table.created_date >= (CURRENT_TIMESTAMP - INTERVAL '24 hour')
                THEN message_table.id END
                ) OVER (PARTITION BY thread_table.id)
            ) as count_today,
            ROW_NUMBER() OVER (PARTITION BY thread_table.id ORDER BY message_table.id) AS rn 
            FROM thread_table 
            LEFT JOIN board_table ON thread_table.board_id = board_table.id 
            LEFT JOIN message_table ON thread_table.id = message_table.thread_id  
            ORDER BY thread_table.id ,board_table.id,message_table.thread_id, message_table_id
            ), 
            UniqueRankedMessages AS (    SELECT *     FROM RankedMessages    WHERE rn = 1       LIMIT 3     OFFSET 0 ) 
            SELECT * FROM(SELECT  DISTINCT ON (id ) * FROM RankedMessages WHERE id IN (SELECT id FROM UniqueRankedMessages)) Z
            ORDER BY id DESC
            """, autoReference = false)
    List<Thread> findAllOnlyIgnoredBoard(Pageable pageable);
    Long countById(Integer id);

    Thread findByIdOnly(Integer id, Pageable pageable);

    @Query(value = """
            WITH RankedMessages AS (
            SELECT thread_table.* ,
            board_table.id AS board_table_id ,
            board_table.name AS board_table_name ,
            board_table.description AS board_table_description ,
            board_table.created_date AS board_table_created_date ,
            board_table.banner AS board_table_banner ,
            message_table.id AS message_table_id ,
            message_table.thread_id AS message_table_thread_id ,
            message_table.body AS message_table_body ,
            message_table.created_date AS message_table_created_date ,
            (COUNT(message_table.id) OVER (PARTITION BY thread_table.id)) as count_all ,
            (COUNT(
                CASE WHEN message_table.created_date >= (CURRENT_TIMESTAMP - INTERVAL '24 hour')
                THEN message_table.id END
                ) OVER (PARTITION BY thread_table.id)
            ) as count_today,
            ROW_NUMBER() OVER (PARTITION BY thread_table.id ORDER BY message_table.id) AS rn 
            FROM thread_table 
            LEFT JOIN board_table ON thread_table.board_id = board_table.id 
            LEFT JOIN message_table ON thread_table.id = message_table.thread_id  
            WHERE thread_table.id = :threadId  
            ORDER BY thread_table.id ,board_table.id,message_table.thread_id, message_table_id
            ), 
            UniqueRankedMessages AS (    SELECT *     FROM RankedMessages    WHERE rn = 1       LIMIT 1     OFFSET 0 ) 
            SELECT * FROM(SELECT  DISTINCT ON (id ) * FROM RankedMessages WHERE id IN (SELECT id FROM UniqueRankedMessages)) Z
            """, autoReference = false)
    Thread getThread(@Param("threadId") Integer threadId);

    Thread findOnly(Pageable pageable);
    Thread findByBoardIgnoredBoardAndMessages(String boardId, Pageable pageable);
}
