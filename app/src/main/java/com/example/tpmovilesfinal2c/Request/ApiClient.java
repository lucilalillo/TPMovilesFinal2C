package com.example.tpmovilesfinal2c.Request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
;

public class ApiClient {

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
       /* // Construimos un cliente HTTP utilizando OkHttpClient para manejar las solicitudes
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // Crear un interceptor para el logging
        // agregar a gradle: implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14'
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);*/
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLBASE)
                //.client(httpClient.build()) // El cliente HTTP configurado
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
        //obtiene listado de inmuebles
        @GET("Inmuebles")
        Call<List<Inmueble>> listaInmuebles(@Header("Authorization") String token);

        //cambiar estado disponible del inmueble
        //se usa en la vista detalle inmueble
        @PUT("Inmuebles/{id}")
        Call<Inmueble> editarEstado(@Header("Authorization") String token, @Path("id")int id );

        //devuelve los inmuebles alquilados
        //se usa en la vista de inquilinos y de contratos
        @GET("Contratos")
        Call<List<Contrato>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

        //este servicio devuelve una lista de inmuebles alquilados del usuario logueado
        //se usa en la vista Contrato
        @GET("Inmuebles/Alquilados")
        Call<List<Inmueble>> obtenerPropiedadesAlquiladas(@Header("Authorization")  String token);

        //Devuelve un contrato especifico, se usa en la vista detalle de contrato
        @GET("Contratos/ObtenerContrato")
        Call<Contrato> obtenerContratoVigente(@Header("Authorization")  String token, @Path ("id") int idCon);

        //devuelve una lista de los pagos de un contrato especifico. se usa en el boton de pagos
        @GET("Pagos/{id}")
        Call<List<Pago>> obtenerPagos(@Header("Authorization") String token, @Path ("id") int idCon);

    }

    /*private ApiClient(){
        //Nos conectamos a nuestra "Base de Datos"
        cargaDatos();
    }
    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;

    }




    //Servicios
    //Para que pueda iniciar sesion
    public Propietario login(String mail, final String password){
        for(Propietario propietario:propietarios){
            if(propietario.getEmail().equals(mail)&&propietario.getContraseña().equals(password)){
                usuarioActual=propietario;
                return propietario;
            }
        }
        return null;
    }


    //Retorna el usuario que inició Sesión
    public Propietario obtenerUsuarioActual(){
        return usuarioActual;
    }

    //Retorna las propiedades del usuario propietario logueado
    public ArrayList<Inmueble> obtnerPropiedades(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Inmueble inmueble:inmuebles){
            if(inmueble.getPropietario().equals(usuarioActual)){
                temp.add(inmueble);
            }
        }
        return temp;
    }

    //Lista de inmuebles con contratos vigentes del Propietario logueado
    public ArrayList<Inmueble> obtenerPropiedadesAlquiladas(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.getInmueble().getPropietario().equals(usuarioActual)){
                temp.add(contrato.getInmueble());
            }
        }
        return temp;
    }


//Dado un inmueble retorna el contrato activo de dicho inmueble

    public Contrato obtenerContratoVigente(Inmueble inmueble){

        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato;
            }
        }
        return null;
    }

    //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble.
    public Inquilino obtenerInquilino(Inmueble inmueble){
        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato.getInquilino();
            }
        }
        return null;
    }
    //Dado un Contrato, retorna los pagos de dicho contrato
    public ArrayList<Pago> obtenerPagos(Contrato contratoVer){
        ArrayList<Pago> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.equals(contratoVer)){
                for(Pago pago:pagos){
                    if(pago.getContrato().equals(contrato)){
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }
    //Actualizar Perfil
    public void actualizarPerfil(Propietario propietario){
        int posición=propietarios.indexOf(propietario);
        if(posición!=-1){
            propietarios.set(posición,propietario);
        }
    }

    //ActualizarInmueble
    public void actualizarInmueble(Inmueble inmueble){
        int posicion=inmuebles.indexOf(inmueble);
        if(posicion!=-1){
            inmuebles.set(posicion,inmueble);
        }
    }

    private void cargaDatos(){

        //Propietarios
        Propietario juan=new Propietario(1,23492012L,"Juan","Perez","juan@mail.com","123","2664553447", R.drawable.juan);
        Propietario sonia=new Propietario(2,17495869L,"Sonia","Lucero","sonia@mail.com","123","266485417",R.drawable.sonia);
        propietarios.add(juan);
        propietarios.add(sonia);

        //Inquilinos
        Inquilino mario=new Inquilino(100,25340691L,"Mario","Luna","Aiello sup.","luna@mail.com","2664253411","Lucero Roberto","2664851422");
        inquilinos.add(mario);

        //Inmuebles
        Inmueble salon=new Inmueble(501,"Colon 340","comercial","salon",2,20000,juan,true,"http://www.secsanluis.com.ar/servicios/salon1.jpg");
        Inmueble casa=new Inmueble(502,"Mitre 800","particular","casa",2,15000,juan,true,"http://www.secsanluis.com.ar/servicios/casa1.jpg");
        Inmueble otraCasa=new Inmueble(503,"Salta 325","particular","casa",3,17000,sonia,true,"http://www.secsanluis.com.ar/servicios/casa2.jpg");
        Inmueble dpto=new Inmueble(504,"Lavalle 450","particular","dpto",2,25000,sonia,true,"http://www.secsanluis.com.ar/servicios/departamento1.jpg");
        Inmueble casita=new Inmueble(505,"Belgrano 218","particular","casa",5,90000,sonia,true,"http://www.secsanluis.com.ar/servicios/casa3.jpg");

        inmuebles.add(salon);
        inmuebles.add(casa);
        inmuebles.add(otraCasa);
        inmuebles.add(dpto);
        inmuebles.add(casita);

        //Contratos
        Contrato uno=new Contrato(701, "05/01/2020","05/01/2021",17000,mario,otraCasa);
        contratos.add(uno);
        //Pagos
        pagos.add(new Pago(900,1,uno,17000,"10/02/2020"));
        pagos.add(new Pago(901,2,uno,17000,"10/03/2020"));
        pagos.add(new Pago(902,3,uno,17000,"10/04/2020"));
*/



    }
