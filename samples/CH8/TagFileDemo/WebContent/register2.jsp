<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib  prefix="html" tagdir="/WEB-INF/tags" %>
<html>
    <html:Header title="Gossip 微網誌"/>
    <body>
    <html:Errors/>
        <h1>會員註冊</h1>
        <form method='post' action='register.do'>
            <table bgcolor=#cccccc>
                <tr>
                    <td>郵件位址：</td>
                    <td><input type='text' name='email' value='${param.email}' size='25' maxlength='100'>
                    </td>
                </tr>
                <tr>
                    <td>名稱（最大16字元）：</td>
                    <td><input type='text' name='username' value='${param.username}' size='25' maxlength='16'></td>
                </tr>
                <tr>
                    <td>密碼（6到16字元）：</td>
                    <td><input type='password' name='password' size='25' maxlength='16'>
                    </td>
                </tr>
                <tr>
                    <td>確認密碼：</td>
                    <td><input type='password' name='confirmedPasswd' size='25' maxlength='16'></td>
                </tr>
                <tr>
                    <td colspan='2' align='center'><input type='submit' value='註冊'></td>
                </tr>
            </table>
        </form>
    </body>
</html>