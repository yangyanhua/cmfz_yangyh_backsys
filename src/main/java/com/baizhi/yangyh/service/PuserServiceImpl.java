package com.baizhi.yangyh.service;
import com.baizhi.yangyh.annotation.LogAnntation;
import com.baizhi.yangyh.annotation.Logging;
import com.baizhi.yangyh.dao.PuserDao;
import com.baizhi.yangyh.entity.PUser;
import com.baizhi.yangyh.entity.Usersite;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PuserServiceImpl implements PuserService {
   @Autowired
    private PuserDao puserDao;
    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public List<PUser> queryAllPuser(@Param("page") Integer page, @Param("rows") Integer rows) {
        List<PUser> list = puserDao.selectAllpage(page,rows);
        return list;
    }
    @Logging(value = "查所有用户条数")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer myTotopage() {

        return puserDao.countDAO();
    }
    @Logging(value = "删除用户",parameters = {@LogAnntation("根据对象删除")})
    @Override
    public void Servicedelete(PUser pUser) {

        puserDao.delete(pUser);
    }
    @Logging(value = "修改用户",parameters = {@LogAnntation("根据对象修改")})
    @Override
    public void Serviceupdate(PUser pUser) {

        puserDao.serviceupdate(pUser);
    }

    @Override
    public List<PUser> Servicefindlist() {
        return  null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer Servicefindtime(int i) {
        return null;
    }
    //查询性别男
    @Logging(value = "查询用户男")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Usersite> Servicefindman() {
        return puserDao.findman();
    }
    //查询性别男
    @Logging(value = "查询用户女")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Usersite> Servicefindwoman() {

        return  puserDao.findwomen();
    }

    @Override
    //@LogAnnotation(methodName = "自定义导入")
    @Transactional(propagation = Propagation.REQUIRED)
    public String insertAppUser(MultipartFile userTable)  {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(userTable.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;
            PUser appUser = new PUser();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                for (int j = 1; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    switch (j) {
                        case 1:
                            appUser.setName(cell.getStringCellValue());//App用户真实姓名
                            break;
                        case 2:
                            /*String md5Password = ShiroMd5Util.getMd5Password(cell
                                            .getStringCellValue(),
                                    new SecureRandomNumberGenerator().nextBytes()
                                            .toHex(), 2);*/
                            appUser.setFhname(cell.getStringCellValue());////App用户法名
                            break;
                        case 3:
                            appUser.setEmail(cell.getStringCellValue());////App用户邮箱
                            break;
                        case 4:
                            appUser.setPhone(cell.getStringCellValue());//App用户手机号
                            break;
                        case 5:
                            appUser.setPassword(cell.getStringCellValue());//App用户密码
                            break;
                        case 6:
                            appUser.setSex(cell.getStringCellValue());//App用户性别
                            break;
                        case 7:
                            appUser.setSite(cell.getStringCellValue());//App用户地址
                            break;
                        case 8:
                            appUser.setHeadurl(cell.getStringCellValue());//App用户头像路径
                            break;
                        case 9:
                            appUser.setSignature(cell.getStringCellValue());//App用户签名
                            break;
                        case 10:
                            appUser.setRegisttime(cell.getDateCellValue());//App用户注册时间
                            break;
                        case 11:
                            appUser.setLastlogtime(cell.getDateCellValue());//App用户登陆时间
                            break;
                        case 12:
                           // appUser.(cell.getStringCellValue());//App用户上师ID
                            break;
                        case 13:
                            appUser.setQq(cell.getStringCellValue());//App用户QQ号
                            break;
                        case 14:
                            appUser.setWeix(cell.getStringCellValue());//App用户微信号
                            break;
                        case 15:
                            appUser.setState(cell.getStringCellValue());//App用户状态
                            break;

                        default:
                            break;
                    }
                }
                puserDao.addAppUser(appUser);
            }
            return "恭喜导入成功";

        } catch (IOException e) {
            e.printStackTrace();
            return "导入失败";
        }

    }

    @Override
    public List<PUser> queryAllPuser() {
        List<PUser> list = puserDao.selectAll();
        return list;
    }
}
