<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <h1>판매중인 전체 상품리스트</h1>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>상품명</th>
                    <th>제조사</th>
                    <th>가격</th>
                    <th>재고</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}" >
                <tr>
                    <td>${product.no}</td>
                    <td>${product.name]}</td>
                    <td>${product.maker}</td>
                    <td>${product.price}</td>
                    <td>${product.stock}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
