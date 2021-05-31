
// // SQL Migration 이랑 충돌 발생하는듯 ? 
// // 주석 나중에사용시 file이름 V3__bulk_update_books.java 로 변경 

// package db.migration;

// import java.sql.ResultSet;
// import java.sql.Statement;

// import org.flywaydb.core.api.migration.BaseJavaMigration;
// import org.flywaydb.core.api.migration.Context;

// public class V3__bulk_update_books extends BaseJavaMigration{
//     @Override
//     public void migrate(Context context) throws Exception {
//         // Statement SQL Query 를 실행하는 인터페이스 
//         // context.getConnection().createStatement() 구현체를 리턴 
//         try (Statement select = context.getConnection().createStatement()) {
//             try (ResultSet rows = select.executeQuery("SELECT id FROM books ORDER BY id")) {
//                 while (rows.next()) {
//                     int id = rows.getInt(1);
                    
//                     String nameToChange = "Tonny";

//                     try (Statement update = context.getConnection().createStatement()) {
//                         update.execute("UPDATE books SET author='" + nameToChange + "' WHERE id=" + id);
//                     }
//                 }
//             }
//         }
//     }
    
// }
