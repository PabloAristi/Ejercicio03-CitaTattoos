package com.pabloaristi.ejercicio03_citatattoos.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CitaTattoo implements Parcelable {
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Date fechaCita;
    private float adelanto;
    private boolean aColor;
    private boolean autorizado = false; //Solo si es menor de edad

    public CitaTattoo(String nombre, String apellidos, Date fechaNacimiento, Date fechaCita, int fianza, boolean aColor, boolean autorizado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCita = fechaCita;
        this.adelanto = fianza;
        this.aColor = aColor;

    }

    public CitaTattoo() {
    }

    protected CitaTattoo(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        adelanto = in.readFloat();
        aColor = in.readByte() != 0;
        autorizado = in.readByte() != 0;
        //Estos dos de abajo a mano
        fechaCita = new Date (in.readLong());
        fechaNacimiento = new Date (in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeFloat(adelanto);
        dest.writeByte((byte) (aColor ? 1 : 0));
        dest.writeByte((byte) (autorizado ? 1 : 0));
        //Estos dos de abajo HAY QUE PONERLOS A MANO
        dest.writeLong(fechaCita.getTime());
        dest.writeLong(fechaNacimiento.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CitaTattoo> CREATOR = new Creator<CitaTattoo>() {
        @Override
        public CitaTattoo createFromParcel(Parcel in) {
            return new CitaTattoo(in);
        }

        @Override
        public CitaTattoo[] newArray(int size) {
            return new CitaTattoo[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public float getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(float adelanto) {
        this.adelanto = adelanto;
    }

    public boolean isaColor() {
        return aColor;
    }

    public void setaColor(boolean aColor) {
        this.aColor = aColor;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
}
