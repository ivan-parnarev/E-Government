package com.example.egovernmentaccesscontrol.service;

import com.example.egovernmentaccesscontrol.domain.geography.CountryStructure;
import com.example.egovernmentaccesscontrol.domain.geography.Node;
import com.example.egovernmentaccesscontrol.domain.geography.factory.NodeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeographyService {

    public void initBulgariaRegions(){
       
        Node bulgaria =  NodeFactory.createNode("България", "");


        Node blagoevgrad =  NodeFactory.createNode("Благоевград", "2700");
        Node burgas =  NodeFactory.createNode("Бургас", "8000");
        Node dobrich =  NodeFactory.createNode("Добрич", "9300");
        Node gabrovo =  NodeFactory.createNode("Габрово", "5300");
        Node haskovo =  NodeFactory.createNode("Хасково", "6300");
        Node kardzhali =  NodeFactory.createNode("Кърджали", "6600");
        Node kyustendil =  NodeFactory.createNode("Кюстендил", "2500");
        Node lovech =  NodeFactory.createNode("Ловеч", "5500");
        Node montana =  NodeFactory.createNode("Монтана", "3400");
        Node pazardzhik =  NodeFactory.createNode("Пазарджик", "4400");
        Node pernik =  NodeFactory.createNode("Перник", "2300");
        Node pleven =  NodeFactory.createNode("Плевен", "5800");
        Node plovdiv =  NodeFactory.createNode("Пловдив", "4000");
        Node razgrad =  NodeFactory.createNode("Разград", "7200");
        Node ruse =  NodeFactory.createNode("Русе", "7000");
        Node shumen =  NodeFactory.createNode("Шумен", "9700");
        Node silistra =  NodeFactory.createNode("Силистра", "7500");
        Node sliven =  NodeFactory.createNode("Сливен", "8800");
        Node smolyan =  NodeFactory.createNode("Смолян", "4700");
        Node sofiaCity =  NodeFactory.createNode("София-град", "1000");
        Node sofiaProvince =  NodeFactory.createNode("Софийска област", "2000");
        Node staraZagora =  NodeFactory.createNode("Стара Загора", "6000");
        Node targovishte =  NodeFactory.createNode("Търговище", "7700");
        Node varna =  NodeFactory.createNode("Варна", "9000");
        Node velikoTarnovo =  NodeFactory.createNode("Велико Търново", "5000");
        Node vidin =  NodeFactory.createNode("Видин", "3700");
        Node vratsa =  NodeFactory.createNode("Враца", "3000");
        Node yambol =  NodeFactory.createNode("Ямбол", "8600");

        List<Node> bulgarianRegions = Arrays.asList(
                blagoevgrad, burgas, dobrich, gabrovo, haskovo, kardzhali, kyustendil,
                lovech, montana, pazardzhik, pernik, pleven, plovdiv, razgrad, ruse,
                shumen, silistra, sliven, smolyan, sofiaCity, sofiaProvince, staraZagora,
                targovishte, varna, velikoTarnovo, vidin, vratsa, yambol
        );

        CountryStructure.getInstance().getStructure().put(bulgaria, bulgarianRegions);
        printStructure();
    }

    public void printStructure(){

        Map<Node, List<Node>> structure =  CountryStructure.getInstance().getStructure();
        for (Map.Entry<Node, List<Node>> nodeListEntry : structure.entrySet()) {
            System.out.println("\t\t\tCountry: " + nodeListEntry.getKey().getName());
            for (Node node : nodeListEntry.getValue()) {
                System.out.println("\tNode: " + node.getName());

            }
        }
    }

}
