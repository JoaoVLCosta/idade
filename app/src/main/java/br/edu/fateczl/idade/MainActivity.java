package br.edu.fateczl.idade;

import java.util.Calendar;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Calendar hoje = Calendar.getInstance();

    private EditText etDia;
    private EditText etMes;
    private EditText etAno;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etDia = findViewById(R.id.etDia);
        etDia.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        etMes = findViewById(R.id.etMes);
        etMes.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        etAno = findViewById(R.id.etAno);
        etAno.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        Button btIdade = findViewById(R.id.btIdade);
        btIdade.setOnClickListener(op -> calcularIdade());

    }

    private void calcularIdade(){
        int diaA = hoje.get(Calendar.DAY_OF_MONTH);
        int mesA = hoje.get(Calendar.MONTH) + 1;
        int anoA = hoje.get(Calendar.YEAR);

        int diaN = Integer.parseInt(etDia.getText().toString());
        int mesN = Integer.parseInt(etMes.getText().toString());
        int anoN = Integer.parseInt(etAno.getText().toString());

        int diaRes = diaA - diaN;
        int mesRes = mesA - mesN;
        int anoRes = anoA - anoN;

        int[] calendario = definirCalendario(anoA);

        if(diaRes < 0){
            mesRes--;
            int m = mesA - 2;
            if(m < 0){
                m = 11;
            }
            diaRes = calendario[m] + diaRes;
        }

        if(mesRes < 0){
            anoRes--;
            mesRes = 12 + mesRes;
        }

        tvResultado.setText(anoRes + " anos, " + mesRes + " meses, " + diaRes + " dias");
    }

    private int[] definirCalendario(int ano){
        int[] calendario = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(bissexto(ano)){
            calendario[1] = 29;
        }
        return calendario;
    }

    private boolean bissexto(int ano){
        if((ano % 4) == 0){
            if((ano % 100) == 0){
                return (ano % 400) == 0;
            }
            return true;
        }
        return false;
    }

}