package com.ss.managesys;

import com.ss.excel.factory.SSExcel07Workbook;
import com.ss.excel.factory.sheet.SSExcel07Sheet;
import com.ss.managesys.entity.vo.tvo.PlanExcelTvo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CommonTestt {

    @Test
    public void ExcelTest(){
        String filePath  = "D:\\ss-Smart_xiAn\\workSpace\\newProdTest.xlsx";
        SSExcel07Sheet sheet = (new SSExcel07Workbook()).open(filePath).getSheet(0);
        ArrayList<PlanExcelTvo> PlanExcelTvos = new ArrayList<>();
        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            PlanExcelTvo PlanExcelTvo = new PlanExcelTvo();
            //内层各项为各列属性值
            for (int j = 0; j <= 4; j++) {
//                if (sheet.getCell(i, j).getCellValue() != null) {
                    switch (j) {
                        case 0:
                            PlanExcelTvo.setTaskName(String.valueOf(sheet.getCell(i, j).getCellValue()));
                            log.info("i:{}==J:{}==={}",i,j,String.valueOf(sheet.getCell(i, j).getCellValue()));
                            break;
                        case 1:
                            PlanExcelTvo.setOperator(String.valueOf(sheet.getCell(i, j).getCellValue()));
                            log.info("i:{}==J:{}==={}",i,j,String.valueOf(sheet.getCell(i, j).getCellValue()));
                            break;
                        case 2:
                            PlanExcelTvo.setPlanDay(Integer.valueOf((String) sheet.getCell(i, j).getCellValue()));
//                            PlanExcelTvo.setPlanDay((Integer) sheet.getCell(i, j).getCellValue());
                            log.info("i:{}==J:{}==={}",i,j,String.valueOf(sheet.getCell(i, j).getCellValue()));
                            break;
                        case 3:
                            PlanExcelTvo.setExaminer(String.valueOf(sheet.getCell(i, j).getCellValue()));
                            log.info("i:{}==J:{}==={}",i,j,String.valueOf(sheet.getCell(i, j).getCellValue()));
                            break;
                        default:
                            log.info("执行了default");
                            break;
                    }
                }
//            }
            PlanExcelTvos.add(PlanExcelTvo);
            System.out.println(PlanExcelTvos);
        }
    }
    @Test
    public void StringTest(){
        String operators = "zagn、张三、李四";
        ArrayList<String> ss = new ArrayList<>();
        if(operators.contains("、")){
            ss = new ArrayList<String >(Arrays.asList(operators.split("、")));
        }
//        else if(operators.contains(",")){
//            ss = new ArrayList<String >(Arrays.asList(operators.split(",")));
//        }else if(operators.contains("，")){
//            ss = new ArrayList<String >(Arrays.asList(operators.split("，")));
//        }
        System.out.println(ss);
        for (String s : ss) {
            System.out.println(s);
        }
    }

    @Test
    public void arrylistTest(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(5);
        System.out.println(integers.get(0));
    }

    @Test
    public void arrylistTest2() {
        ArrayList<Integer> objectName = new ArrayList<Integer>();
        objectName.add(11);
        objectName.add(88);
        objectName.add(999);
        objectName.add(100);

        List<Integer> objectName2 = objectName.subList(1, objectName.size());
        System.out.println(objectName2);
    }

}
