package br.com.home.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import br.com.home.model.Produto;

/**
 * 
 * @author Eduardo Moritz
 * @ApplicationScoped: Manter listas enquanto servidor estiver rodando
 *
 */
@ApplicationScoped
public class ProdutoService {
    
    private final static String[] descr;
    private final static String[] detail;
    private final static String[] detail2;
     
    static {         
        descr = new String[13];
        descr[0] = "BMW";
        descr[1] = "Mercedes";
        descr[2] = "Volvo";
        descr[3] = "Audi";
        descr[4] = "Renault";
        descr[5] = "Fiat";
        descr[6] = "Volkswagen";
        descr[7] = "Honda";
        descr[8] = "Jaguar";
        descr[9] = "Ford";
        descr[10] = "Toyota";
        descr[11] = "Hyundai";
        descr[12] = "Mazda";   
        
        detail = new String[13];
        detail[0] = "Vela de Ignição";
        detail[1] = "Junta do Cabeçote";
        detail[2] = "Amortecedor Dianteiro";
        detail[3] = "Disco de Freio";
        detail[4] = "Radiador do Motor";
        detail[5] = "Válvula Termostática";
        detail[6] = "Filtro de ar do Motor";
        detail[7] = "Cebolão do Radiador";
        detail[8] = "Cilindro mestre do freio";
        detail[9] = "Sensor de Temperatura da Água";
        detail[10] = "Parafuso do motor";
        detail[11] = "Caixa satélite";
        detail[12] = "Pastilha de Freio";
        
        detail2 = new String[13];
        detail2[0] = "Filtro de óleo";
        detail2[1] = "Filtro de ar";
        detail2[2] = "Bomba de água";
        detail2[3] = "Retentor de cambota";
        detail2[4] = "Pastilhas de travão";
        detail2[5] = "Discos de travão";
        detail2[6] = "Vareta do óleo";
        detail2[7] = "Junta do cárter";
        detail2[8] = "Corrente de distribuição";
        detail2[9] = "Polia da cambota";
        detail2[10] = "Filtro da direção";
        detail2[11] = "Filtro da transmissão";
        detail2[12] = "Injector";
    }
     
    public List<Produto> createProdutos(int size) {
        List<Produto> list = new ArrayList<Produto>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Produto(getRandomId(), getRandomDescricao(), getRandomValor(), getRandomDetalhes()));
        }
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    
    private String getRandomDescricao() {
        return descr[(int) (Math.random() * 13)];
    }
     
    private BigDecimal getRandomValor() {
        return new BigDecimal(Math.random() * 100).setScale(2, RoundingMode.HALF_EVEN);
    }
    
    private List<String> getRandomDetalhes() {
    	List<String> list = new ArrayList<String>();
    	for(int i = 0; i < 5; i++) {
    		list.add(detail[(int) (Math.random() * 13)]);
    		list.add(detail2[(int) (Math.random() * 13)]);
    	}
    	return list;
    }
    
    public List<String> getDetalhes() {
    	return Arrays.asList(detail);
    }
     
    public List<String> getDescricao() {
        return Arrays.asList(descr);
    }

}
