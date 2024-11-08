package com.example.tpmovilesfinal2c.Request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.tpmovilesfinal2c.Modelo.Tipo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.Modelo.Pago;
import com.example.tpmovilesfinal2c.Modelo.Propietario;
import com.example.tpmovilesfinal2c.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
;

public class ApiClient {
                                                          //104 living
    private static final String URLBASE="http://192.168.0.104:5001/";
    private static  PostInterface postInterface;
    private static SharedPreferences sharedPreferences;

    public static SharedPreferences conectar(Context context){
        if (sharedPreferences==null){
            sharedPreferences = context.getSharedPreferences("token.dat",0);
        }
        return  sharedPreferences;
    }

    public static PostInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        // Construimos un cliente HTTP utilizando OkHttpClient para manejar las solicitudes
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // Crear un interceptor para el logging
        // agregar a gradle: implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14'
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLBASE)
                .client(httpClient.build()) // El cliente HTTP configurado
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

        return postInterface;
    }

    public interface  PostInterface{

        //Servicios que usamos en propietarios
        //Se usa en la vista de login
        @FormUrlEncoded
        @POST("Propietarios/Login")
        Call<String> login(@Field("Email") String email, @Field("Clave") String clave);

        //servicio que devuelve un propietario
        //Se usa en la vista de perfil
        @GET("Propietarios")
        Call<Propietario> obtenerPropietario(@Header("Authorization") String token);

        //servicio que edita los datos del propietario
        //Se usa en la vista de perfil
        @PUT("Propietarios")
        Call<Propietario> editarPropietario(@Header("Authorization") String token, @Body Propietario propietario);
        //servicio que cambia la contraseña
        //se usa en la vista perfil
        @FormUrlEncoded
        @PATCH("propietarios/cambiarpass")
        Call<Propietario> cambiarpass(@Header("Authorization")String token,
                                      @Field("clVieja")String cv,
                                      @Field("clNueva")String cn);

        //olvide mi contraseña,se una en la vista login
        @FormUrlEncoded
        @POST("propietarios/email")
        Call<String>resetearpass(@Field("email")String email);

        //obtiene listado de inmuebles
        @GET("Inmuebles")
        Call<List<Inmueble>> listaInmuebles(@Header("Authorization") String token);

        //guarda Inmueble nuevo
        @Multipart
        @POST("Inmuebles/")
        Call<Inmueble> crearInmueble(@Header("Authorization")String token,
                                     @Part MultipartBody.Part imagen, @Part("direccion") RequestBody direccion,
                                     @Part("ambientes")RequestBody ambientes, @Part("importe")RequestBody importe,
                                     @Part("uso")RequestBody uso, @Part("tipoId")RequestBody tipoId,
                                     @Part("disponible")RequestBody disponible);

        //cambiar estado disponible del inmueble
        //se usa en la vista detalle inmueble
        @PATCH("Inmuebles/{id}")
        Call<Inmueble> editarEstado(@Header("Authorization") String token, @Path("id")int id );

        //devuelve los inmuebles alquilados
        //se usa en la vista de inquilinos y de contratos
        @GET("Contratos")
        Call<List<Contrato>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

        //agrega un nuevo inmueble del propietario logueado
        //se usa en la vista detalle Inmueble
        @Multipart
        @POST("inmuebles/")
        Call<Inmueble> crearInmueble(@Part("imagen")String url, @Field("direccion")String direccion,
                                     @Field("ambientes")int ambientes, @Field("importe")int importe,
                                     @Field("uso")String uso, @Field("tipoId")int tipoId,
                                     @Field("disponible")boolean disponible);

        //devuelve una lista de los pagos de un contrato especifico. se usa en el boton de pagos
        @GET("Pagos/{id}")
        Call<List<Pago>> obtenerPagos(@Header("Authorization") String token, @Path ("id") int idCon);

        //Tipos
        //devuelve la lista de tipos para el spinner
        @GET("tipos/listatipos")
        Call<List<Tipo>> listaTipos(@Header("Authorization")String token);

    }
}
