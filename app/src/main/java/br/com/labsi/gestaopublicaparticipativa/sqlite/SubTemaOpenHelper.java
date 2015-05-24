package br.com.labsi.gestaopublicaparticipativa.sqlite;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import br.com.labsi.gestaopublicaparticipativa.dto.SubTemaDTO;

/**
 * Created by Marcelo on 20/01/2015.
 */
public class SubTemaOpenHelper extends SQLiteOpenHelper {


    private static String dbName = "lojavirtual.db";

    private static int version = 1;
    private static String sql = "CREATE TABLE TB_PESSOA (ID_PESSOA INT PRIMARY KEY AUTOINCREMENT, NOME TEXT NOT NULL, " +
            "ENDERECO TEXT NOT NULL, CPF TEXT NOT NULL, PROFISSAO INT NOT NULL, SEXO CHAR NOT NULL)";

    public SubTemaOpenHelper(Context context) {
        super(context, dbName, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
/*
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE TB_PESSOA (ID_PESSOA INT PRIMARY KEY AUTOINCREMENT, NOME TEXT NOT NULL, " +
                "ENDERECO TEXT NOT NULL, CPF TEXT NOT NULL, PROFISSAO INT NOT NULL, SEXO CHAR NOT NULL)");
        db.execSQL(sql.toString());
*/
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public List<SubTemaDTO> listar() {
        List<SubTemaDTO> lista = new ArrayList<SubTemaDTO>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, "TB_PESSOA", null, null, null, null, null, "ID_PESSOA", null, null);

        while (cursor.moveToNext()) {
            SubTemaDTO subTemaDTO = new SubTemaDTO();
            subTemaDTO.setIdSubTema(cursor.getInt(cursor.getColumnIndex("id_subtema")));
            subTemaDTO.setNomeSubTema(cursor.getString(cursor.getColumnIndex("nome_subtema")));


            lista.add(subTemaDTO);
        }
        return lista;
    }
}
