package com.example.demo.completablefuture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class Validations {

    public static void main(String[] args) {
        createParams();
    }


    private static void createParams(){
        Params params = new Params();
        System.out.println(params.isIdGeneral(null));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Params {
        private String id;
        private String name;
        private static final String idGeneral = "UUUID30493";
        private Boolean isIdGeneral(String id){
            Objects.requireNonNull(id, "Id Must has a  value " +id);
            if(id.equals(idGeneral)){
                return true;
            }else
                return false;
        }
    }
}
