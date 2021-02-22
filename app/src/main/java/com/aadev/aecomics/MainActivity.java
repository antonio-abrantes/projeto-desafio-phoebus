package com.aadev.aecomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.aadev.aecomics.adapters.AdapterComic;
import com.aadev.aecomics.helpers.ApiMarvelConfig;
import com.aadev.aecomics.helpers.GenerateRareComics;
import com.aadev.aecomics.listener.RecyclerItemClickListener;
import com.aadev.aecomics.models.Comic;
import com.aadev.aecomics.models.DataComics;
import com.aadev.aecomics.models.ResponseComics;
import com.aadev.aecomics.services.MarvelService;
import com.aadev.aecomics.services.RetrofitObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerComics;
    private AdapterComic adapterComic;
    private Retrofit retrofit;
    private List<Comic> comicResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitObject.getRetrofitObject();
        getComics();
    }

    private void getComics(){
        MarvelService service = retrofit.create(MarvelService.class);
        String timestamp = Long.toString(System.currentTimeMillis());
        Call<ResponseComics> call = service.getComics(
                timestamp , ApiMarvelConfig.API_PUBLIC_KEY, ApiMarvelConfig.getHashMd5(timestamp)
        );

        call.enqueue(new Callback<ResponseComics>() {
            @Override
            public void onResponse(Call<ResponseComics> call, Response<ResponseComics> response) {
                if(response.isSuccessful()){
                    ResponseComics result = response.body();

                    //Aqui pega os dados que vamos tratar
                    DataComics dataComics = result.getData();

                    ArrayList<Comic> resultList = dataComics.getResults();
                    GenerateRareComics.generateRareComics(resultList);
                    comicResults.addAll(resultList);

                    configRecyclerView();
                }else {
                    System.out.println("Deu depois de ter entrado na requisição");
                }
            }

            @Override
            public void onFailure(Call<ResponseComics> call, Throwable t) {
                System.out.println("Erro na requisição dentro da classe");
            }
        });
    }

    public void configRecyclerView(){
        adapterComic = new AdapterComic(comicResults, getApplicationContext());
        recyclerComics = findViewById(R.id.recyclerComics);
        recyclerComics.setHasFixedSize(true);
        recyclerComics.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerComics.setAdapter(adapterComic);

        //Evento de click dos itens
        recyclerComics.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerComics,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Comic comic = comicResults.get(position);
                                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                                try {
                                    intent.putExtra("id", comic.id);
                                    intent.putExtra("title", comic.title);
                                    intent.putExtra("description", comic.description);
                                    intent.putExtra("prices", comic.prices.get(0).price);
                                    intent.putExtra("images", comic.images.get(0).path + "." +comic.images.get(0).extension);
                                    intent.putExtra("rare", comic.rare);

                                    System.out.println(comic.prices.get(0).price);

                                    startActivity(intent);
                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), "Erro ao carregar dados", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }
}
