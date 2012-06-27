<FORM name="sortForm" method="post" class="margin: 0">
  <INPUT type="hidden" name="orderBy" value='<bean:write name="pagination" property="orderBy" />' >
  <INPUT type="hidden" name="use" value="sort">
</FORM>
<script>
function sort(by) {
	sortForm.action="<%=request.getParameter("url")%>";
	sortForm.orderBy.value=by;
	sortForm.submit();
}
</script>