function gotoPage(pagenum){
	var Oform = document.getElementById("PageForm");
	Oform.jumpPage.value = pagenum;
	Oform.submit();
	return false;
}
/**
 * ��̬���س�Listҳ���²��ķ�ҳ������
 * @return ���س����ӵ�HTML���룬������ҳ��ֱ�Ӽ��뼴�ɡ� 
 */
function jumpPageSpan(page, totalPages) {
	var strSelect = "";
	for ( var i = -3; i < 3; i++) {
		var temop = page + i;
		if (temop > 0 && temop < totalPages && i != 0)
			strSelect = strSelect
					+ "&nbsp;<a href=\"javascript:gotoPage(\'"+temop+"\')\">"
					+ "[" + temop + "]" + "</a>"
		if (i == 0)
			strSelect = strSelect + "&nbsp;[" + temop + "]";
	}
	return strSelect;
}