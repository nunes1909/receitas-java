package com.example.receita.ui.activity;

import static com.example.receita.ui.activity.ConstantesActivities.MENSAGEM_TITULO_VAZIO;
import static com.example.receita.ui.activity.ConstantesActivities.NOVA_RECEITA_TITULO_APPBAR;
import static com.example.receita.ui.activity.ConstantesActivities.POSICAO;
import static com.example.receita.ui.activity.ConstantesActivities.RECEITA;
import static com.example.receita.ui.activity.ConstantesActivities.TEXTO_VAZIO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receita.R;
import com.example.receita.model.Receita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NovaReceitaActivity extends AppCompatActivity {

    private TextView campoTitulo;
    private TextView campoDescricao;
    private ImageView imagemAlert;
    private FloatingActionButton botaoSalvaReceita, botaoLimpaReceita;
    private Spinner spinnerTipo;
    private int tipoReceita;
    private int posicaoRecebida;
    private long idRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita);
        campoTitulo = findViewById(R.id.nova_receita_titulo);
        campoDescricao = findViewById(R.id.nova_receita_descricao);
        imagemAlert = findViewById(R.id.nova_receita_alert_titulo);
        spinnerTipo = findViewById(R.id.nova_receita_tipo_spinner);

        configuraBotoes();
        configuraSpinnerTipo();
        verificaDadosRecebidos();
    }

    private void configuraBotoes() {
        botaoSalvaReceita = findViewById(R.id.nova_receita_fab_salva_receita);
        botaoSalvaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacaoFormulario()) finish();
            }
        });

        botaoLimpaReceita = findViewById(R.id.nova_receita_fab_limpa_receita);
        botaoLimpaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campoTitulo.setText(TEXTO_VAZIO);
                campoDescricao.setText(TEXTO_VAZIO);
                spinnerTipo.setSelection(0);
            }
        });
    }

    private boolean validacaoFormulario() {
        boolean validacao;
        String tituloObtido = campoTitulo.getText().toString().trim();
        String descricaoObtida = campoDescricao.getText().toString();

        if (tituloObtido == null || tituloObtido.equals(TEXTO_VAZIO)) {
            Toast.makeText(
                    this,
                    MENSAGEM_TITULO_VAZIO,
                    Toast.LENGTH_LONG).show();
            campoTitulo.setTextColor(getResources().getColor(R.color.colorAlert));
            campoTitulo.setHintTextColor(getResources().getColor(R.color.colorAlert));
            imagemAlert.setVisibility(View.VISIBLE);
            validacao = false;
        } else {
            Receita receitaCriada = new Receita(tituloObtido, descricaoObtida, tipoReceita);
            Intent salvaReceita = new Intent();
            salvaReceita.putExtra(RECEITA, receitaCriada);
            salvaReceita.putExtra(POSICAO, posicaoRecebida);
            salvaReceita.putExtra("id", idRecebido);
            setResult(RESULT_OK, salvaReceita);
            validacao = true;
        }
        return validacao;
    }

    private void configuraSpinnerTipo() {
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoReceita = adapterView.getPositionForView(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void verificaDadosRecebidos() {
        Intent dados = getIntent();
        if (dados.hasExtra(RECEITA)){
            setTitle(NOVA_RECEITA_TITULO_APPBAR);
            botaoSalvaReceita.setImageResource(R.drawable.ic_action_edita_receita);
            Receita receitaRecebida = (Receita) dados.getSerializableExtra(RECEITA);
            posicaoRecebida = dados.getIntExtra(POSICAO, -1);
            idRecebido = dados.getLongExtra("id", -1);
            campoTitulo.setText(receitaRecebida.getTitulo());
            campoDescricao.setText(receitaRecebida.getDescricao());
            spinnerTipo.setSelection(receitaRecebida.getTipo());
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.item_menu_salva, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.item_salva){
//            String tituloObtido = campoTitulo.getText().toString().trim();
//            String descricaoObtida = campoDescricao.getText().toString();
//
//            if (tituloObtido == null || tituloObtido.equals("")) {
//                Toast.makeText(
//                        this,
//                        "Para salvar preencha o título!",
//                        Toast.LENGTH_LONG).show();
//                campoTitulo.setHintTextColor(getResources().getColor(R.color.colorAlert));
//                imagemAlert.setVisibility(View.VISIBLE);
//            } else {
//                Receita receitaCriada = new Receita(tituloObtido, descricaoObtida);
//                Intent salvaReceita = new Intent();
//                salvaReceita.putExtra("receita", receitaCriada);
//                setResult(RESULT_OK, salvaReceita);
//                finish();
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
}