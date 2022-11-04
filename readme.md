## Firebase
#### Projeto para gerenciamento do Firebase utilizando o AndroidStudio.
- RealtimeDatabase
- Auth
- Storage
---
## Preparação
---
> Classe Produto
```bash
package andpedroso.android.firebasedatabaseauthstorage;

public class Produto {
    private String descricao;
    private String marca;
    private Double preco;
    public Produto() {
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
```
> Classe Usuario
```bash
package andpedroso.android.firebasedatabaseauthstorage;

public class Usuario {
    private String nome;
    private String sobrenome;
    private int idade;
    public Usuario() {
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
```
> activity_main
```bash
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <ImageView
        android:id="@+id/celular_img"
        android:layout_width="200dp"
        android:layout_height="100dp"
        android:layout_marginTop="5dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/celular" />
    <Button
        android:id="@+id/upload_btn"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:text="UPLOAD"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/celular_img" />
    <Button
        android:id="@+id/delete_btn"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:text="DELETE"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/upload_btn" />
    <ImageView
        android:id="@+id/padrao_img"
        android:layout_width="200dp"
        android:layout_height="100dp"
        android:layout_marginTop="5dp"
        android:src="@drawable/padrao"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/delete_btn" />
    <Button
        android:id="@+id/download_btn"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:text="DOWNLOAD"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/padrao_img" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
> Imagem
#### Salvar duas imagens com extensão .jpg, no caso é celular.jpg e padrao.jpg
#### Uma será utilzada para o Storage e outra para ser carregada sem a utilização do que foi armazenado no Storage.
> variáveis
```bash
//************************REAL TIME DATA BASE*************************

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    //************************AUTH****************************************

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //***********************STORAGE**************************************

    private ImageView imageFoto, imgPadrao;
    private Button buttonUpload, buttonDownload, buttonDelete;
```
---
## RealtimeDatabase
---
> Adicionar valores
```bash
reference.child("pontos").setValue(100);
```
> Criando nós
```bash
reference.child("usuarios").child("002").child("nome").setValue("Valdemar");
```
> Adicionar Usuário
```bash`
DatabaseReference usuarios = reference.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("André");
        usuario.setSobrenome("Pedroso");
        usuario.setIdade(36);
        usuarios.child("001").setValue(usuario);
```
> Adicionar Produto
```bash
DatabaseReference produtos = reference.child("produtos");
        Produto produto = new Produto();
        produto.setDescricao("Nexus");
        produto.setMarca("LG");
        produto.setPreco(899.99);
        produtos.child("001").setValue(produto);
```
> Criar usuário
```bash
DatabaseReference usuarios = reference.child("usuarios").child("001");
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
```
---
## Auth
---
> Criar usuário para autenticação
```bash
mAuth.createUserWithEmailAndPassword(
                "email@email.com", "ja12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) Log.i("CreateUser", "Sucesso");
                        else Log.i("CreateUser", "Erro");
                    }
                });
```
> Verificar usuário logado
```bas
if (mAuth.getCurrentUser() != null) Log.i("CheckUser", "Logado");
        else Log.i("CheckUser", "Deslogado");
```
> Deslogar usuário
```bash
mAuth.signOut();
```
> Logar usuário
```bash
mAuth.signInWithEmailAndPassword(
                "email@email.com", "ja12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) Log.i("SignInUser", "Logado");
                        else Log.i("SignInUser", "Erro");
                    }
                });
```
> Push para gerar identifidor único
```bash
DatabaseReference usuarios = reference.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("Logan");
        usuario.setSobrenome("Krodkfl");
        usuario.setIdade(40);
        usuarios.push().setValue(usuario);
```
---
## Database Filtros
---
> Filtro de nomes
```bash
DatabaseReference usuarios = reference.child("usuarios");
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
        });
```
---
## Storage
---
> Upload
```bash
imageFoto = findViewById(R.id.celular_img);
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
        });
```
> Delete
```bash
buttonDelete = findViewById(R.id.delete_btn);
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
        });
```
> Download
```bash
buttonDownload = findViewById(R.id.download_btn);
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
        });
```
---
## Rodar o projeto
---
(AndroidStudio)[https://developer.android.com/]
#### Rodar em run com um emulador ou um despositivo que permita o debbug
#### Ter uma conta no Firebase, criar um projeto e vincular o app com o projeto utilizando os serviços de RealtimeDabase, Auth e Cloud Storage.
---
## Créditos
---
### André Moura Pedroso
#### Web, Mobile e Games
### Jamilton Damasceno
#### Desenvolvimento Android Completo 2022 - Crie 18 Apps