package andpedroso.android.firebasedatabaseauthstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //************************REAL TIME DATA BASE*************************

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    //************************AUTH****************************************

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //***********************STORAGE**************************************

    private ImageView imageFoto, imgPadrao;
    private Button buttonUpload, buttonDownload, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //********************REAL TIME DATABASE*************************

        //**************************ADICIONANDO VALORES********************************

        //reference.child("pontos").setValue(100);

        //**************************ADICIONANDO VALORES COM NOVO NÓ********************

        //reference.child("usuarios").child("002").child("nome").setValue("Valdemar");

        //**************************ADICIONANDO VALORES COM UM MODELO*******************

        /*DatabaseReference usuarios = reference.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("André");
        usuario.setSobrenome("Pedroso");
        usuario.setIdade(36);
        usuarios.child("001").setValue(usuario);*/
        /*DatabaseReference usuarios = reference.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("Valdemar");
        usuario.setSobrenome("Pedroso");
        usuario.setIdade(60);
        usuarios.child("002").setValue(usuario);*/
        /*DatabaseReference produtos = reference.child("produtos");
        Produto produto = new Produto();
        produto.setDescricao("Nexus");
        produto.setMarca("LG");
        produto.setPreco(899.99);
        produtos.child("001").setValue(produto);*/

        //**********************RECUPERANDO DADOS**************************************

        /*DatabaseReference usuarios = reference.child("usuarios");
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //*****************RECUPERANDO DADOS COM A SELEÇÃO DE UM NÓ********************

        /*DatabaseReference usuarios = reference.child("usuarios").child("001");
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //***************************CRIANDO USUÁRIO PARA AUTENTICAÇÃO*****************

        /*mAuth.createUserWithEmailAndPassword(
                "email@email.com", "ja12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) Log.i("CreateUser", "Sucesso");
                        else Log.i("CreateUser", "Erro");
                    }
                });*/

        //*********************VERIFICAR USUÁRIO LOGADO*********************************

        /*if (mAuth.getCurrentUser() != null) Log.i("CheckUser", "Logado");
        else Log.i("CheckUser", "Deslogado");*/

        //**********************DESLOGAR USUÁRIO****************************************

        //mAuth.signOut();

        //***********************LOGAR USUÁRIO******************************************

        /*mAuth.signInWithEmailAndPassword(
                "email@email.com", "ja12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) Log.i("SignInUser", "Logado");
                        else Log.i("SignInUser", "Erro");
                    }
                });*/

        //*********************GERAR IDENTIFICADOR ÚNICO*******************************

        /*DatabaseReference usuarios = reference.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("Logan");
        usuario.setSobrenome("Krodkfl");
        usuario.setIdade(40);
        usuarios.push().setValue(usuario);*/

        //******************FILTROS - IDENTIFICADOR***********************************

        /*DatabaseReference usuarios = reference.child("usuarios");
        DatabaseReference usuarioPesquisa = usuarios.child("-NEvHXc3IBROAgsjzlT0");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //*********************FILTROS - IDENFICADOR SELETOR DE CLASSE***************

        /*DatabaseReference usuarios = reference.child("usuarios");
        DatabaseReference usuarioPesquisa = usuarios.child("-NEvHht-5Q0BOfwC8Fi4");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                Log.i("DADOS",
                        "nome: " + dadosUsuario.getNome() +
                                ", idade: " + dadosUsuario.getIdade() +
                                ".");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //************************FILTROS - QUERY***********************************

        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("André");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //*********************FILTROS - PESQUISA ORDENADA************************

        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //*********************FILTROS CONDICIONAIS****************************

        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40);
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(40);
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByChild("idade")
                .startAt(18).endAt(30);
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        /*DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByChild("nome")
                .startAt("A").endAt("A" + "\uf8ff");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("DADOS", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //*************************STORAGE******************************************

        /*imageFoto = findViewById(R.id.celular_img);
        buttonUpload = findViewById(R.id.upload_btn);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //**********************CONFIGURAÇÃO***************************************

                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();
                //Recuperar bitmap da imagem
                Bitmap bitmap = imageFoto.getDrawingCache();
                //Comprimir bitmap para png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);
                //Converter baos para pixel em matriz de bytes
                byte[] dadosImagem = baos.toByteArray();
                //Definir nós
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("img");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                //***********************UPLOAD********************************************

                //Nome da imagem randomizado
                //String nomeArquivo = UUID.randomUUID().toString();
                //StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg");

                //Retornar objeto para controlar upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                MainActivity.this,
                                "Upload failed: " +
                                e.getMessage().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(
                                        MainActivity.this,
                                        "Upload concluded: " + url.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });*/

        //***********************************DELETE*****************************

        /*buttonDelete = findViewById(R.id.delete_btn);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("img");
                StorageReference imagemRef = imagens.child("celular.jpeg");
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                MainActivity.this,
                                "Delete Error",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(
                                MainActivity.this,
                                "Delete Sucsess",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        //****************************DOWNLOAD**********************************

        /*buttonDownload = findViewById(R.id.download_btn);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("img");
                StorageReference imagemRef = imagens.child("celular.jpeg");
                imgPadrao = findViewById(R.id.padrao_img);
                imagemRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(MainActivity.this).load(uri).into(imgPadrao);
                        Toast.makeText(
                                MainActivity.this,
                                "Sucesso ao alterar.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/
    }
}