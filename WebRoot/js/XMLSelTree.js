/************************************************
** 文件名:
** Copyright (c) 2003
** 创建人:	moonpiazza
** 日 期:	2003.6.29
** 修改人:
** 日 期:
** 描 述:	XML菜单树
**
** 版 本:	1.0
************************************************/

var m_oConfig ;
function TreeConfig(p_sXSLFile, p_oSrcDiv, p_bAutoGoFisrtLeaf, p_bChangeLeafImg)
{


	this.bImgAsync			= true ;		// 点击图标的时候是否不选中该节点
											// true:  不选中该节点;
											// false: 选中该节点

	this.bShowElseBranch	= true ;		// 是否只显示当前菜单分枝以外的菜单分枝
											// true:  只显示当前1个分枝，隐藏其他分枝;
											// false: 显示已经展开的分枝

	if (true == p_bChangeLeafImg)			// 当叶节点获得焦点，是否改变图标
		this.bChangeLeafImg	= true;			// true:  改变;
	else									// false: 不改变
		this.bChangeLeafImg	= false;


	this.bDataAsync			= true ;		// 异步读取数据(每次是否都从新读入数据)
											// true:  不是;
											// false: 是


	this.bCheckDataAsync	= true ;		// 异步读取数据(每次是否都从新读入数据)
											// true:  不是;
											// false: 是


	this.bUseCheck			= true ;		// 是否启用选择

	this.sSelVal			= "" ;			// 选择的值
	this.oSrcDiv			= p_oSrcDiv ;	//
	this.sCheckKey			= "," ;			// checkdata 分隔符

	this.bCheckAsync		= true;			// 当节点获得焦点，是否选中
											// true:  不选中;
											// false: 选中

	this.bCheckShowBranch	= false;		// 当枝节点选中，是否展开子节点
											// true:  展开;
											// false: 不展开

	if (true == p_bAutoGoFisrtLeaf)			// 是否自动点击当前节点下第一个有'链接'的节点
		this.bAutoGoFisrtLeaf	= true;		// true:  自动;
	else									// false: 不自动
		this.bAutoGoFisrtLeaf	= false;

	this.bAutoScroll		= false ;

	this.oCurrentNode		= "moonpiazza" ;				// 当前节点
	this.sLoadingText		= "Loading..." ;

	this.sClsMouseDown		= "clsMouseDown" ;
	this.sClsMouseOver		= "clsMouseOver" ;
	this.sClsCurrentFocus	= "clsCurrentFocus"



	this.oXSLDoc = new ActiveXObject("Microsoft.XMLDOM") ;	// xsl文档对象，显示xml数据
	this.oXSLDoc.async = false ;
	this.oXSLDoc.load(p_sXSLFile) ;
}





/************************************************
** InitTree()
************************************************/
function InitTree(p_sXMLFile, p_sXSLPath, p_oSrcDiv, p_bAutoGoFisrtLeaf, p_bChangeLeafImg)
{
	var bIE	= (navigator.appName=="Microsoft Internet Explorer")?true:false ;

	if (!bIE)
	{
		alert('你的浏览器不是IE浏览器,XMlSelTree不支持此浏览器，谢谢你的支持！');
		return false ;
	}
	
	var sXSLPath	= ("/" == (p_sXSLPath.charAt((p_sXSLPath.length-1))) ) ? p_sXSLPath:(p_sXSLPath + '/')
	var sXSLFile	= sXSLPath + GetXSLFileName() ;
	m_oConfig	= new TreeConfig(sXSLFile, p_oSrcDiv, p_bAutoGoFisrtLeaf, p_bChangeLeafImg) ;

	var oXMLDom	= new ActiveXObject("Microsoft.XMLDOM") ;
		oXMLDom.async	= false ;
		oXMLDom.load(p_sXMLFile) ;

	var sHtml	= oXMLDom.transformNode(m_oConfig.oXSLDoc) ;

	p_oSrcDiv.innerHTML	= sHtml ;

	if (m_oConfig.bAutoGoFisrtLeaf)
	{
		GoFisrtLink( p_oSrcDiv.getElementsByTagName("DIV")[0] ) ;
	}
}

/*************************************************************************
** GetXSLFileName()
*************************************************************************/
function GetXSLFileName()
{
	var sReturnVal ;
	var oAgent	= navigator.userAgent ;
	var fIEVersion	= parseFloat(oAgent.substring(oAgent.indexOf("MSIE ")+5, oAgent.indexOf(";",oAgent.indexOf("MSIE ")))) ;

	if (fIEVersion < 6)
	{
		sReturnVal	= "XMLSelTreeforIE5.xsl" ;
	}
	else
	{
		sReturnVal	= "XMLSelTree.xsl" ;
	}

	return sReturnVal ;
}
/*************************************************************************
** selectstart()
*************************************************************************/
function selectstart()
{
	window.event.cancelBubble = true;
	window.event.returnValue = false;
	return false;
}

//-------------------------------------------------------------------------------------------------
//					获得(设置)数据 GET (SET) FUNCTIONS
//-------------------------------------------------------------------------------------------------


/*************************************************************************
** GetXML(p_sXMLSrc, p_oContainerElement, p_sContainerElementState)
*************************************************************************/
function GetXML(p_sXMLSrc, p_oContainerElement)
{
    var sHtml	= "" ;

	var oXMLDom	= new ActiveXObject("Microsoft.XMLDOM") ;
		oXMLDom.async	= false ;
		oXMLDom.load(p_sXMLSrc) ;

    if ( (oXMLDom != null) && (oXMLDom.xml != "") )
    {
        if (m_oConfig.oXSLDoc.xml != "")
        {
			sHtml	= oXMLDom.transformNode(m_oConfig.oXSLDoc) ;
        }
        if ( (sHtml != null) && (sHtml != "") )
        {
            p_oContainerElement.innerHTML	= sHtml ;
            p_oContainerElement.className	= "shown" ;
        }
    }
}


/************************************************
** GetChildElement(p_oSrcElement, p_sType)
** 功能：定位某节点中子节点对象(子节点类型唯一:type)
************************************************/
function GetChildElement(p_oSrcElement, p_sType)
{
	var i	= 0 ;
	var oReturnValue	= null ;
	var oChildren		= null ;

	if(p_oSrcElement!=null)
	{
		oChildren	= p_oSrcElement.children ;
	}

	if (oChildren != null)
	{
		for (i=0; i < oChildren.length; i++)
		{
			if ( (oChildren[i].type==p_sType) || (p_sType == null) )
			{
				oReturnValue	= oChildren[i] ;
				break ;
			}
		}
	}

	return oReturnValue ;
}


/************************************************
** GetParentElement(p_oSrcElement)
************************************************/
function GetParentElement(p_oSrcElement)
{
	var oReturnValue	= null ;

	if ( (p_oSrcElement!=null) && (p_oSrcElement.parentElement != null) )
	{
		oReturnValue	= p_oSrcElement.parentElement.parentElement ;
	}

	if ( (oReturnValue != null) && (oReturnValue.type!="branch") )
	{
		oReturnValue	= null ;
	}

	return oReturnValue ;
}


/************************************************
** GetImgSrc(p_sImgSrc, p_sType)
** 功能：获得图标路径
************************************************/
function GetImgSrc(p_sImgSrc, p_sType)
{
	var sReturnVal ;
	var aImgSrcInfo ;
	var sImgPath, sImgType ;

	aImgSrcInfo	= p_sImgSrc.split(".") ;
	sImgType	= aImgSrcInfo[ (aImgSrcInfo.length-1) ] ;

	sImgPath	= p_sImgSrc.substring(0, (p_sImgSrc.length - (sImgType.length + 2) ) ) ;

	sReturnVal	= sImgPath + p_sType + "." + sImgType ;

	return sReturnVal ;
}

/************************************************
** GoFisrtLink(p_oSrcElement)
************************************************/
function GoFisrtLink(p_oSrcElement)
{
	if (null == p_oSrcElement) return ;

	if (m_oConfig.oCurrentNode != "moonpiazza")
	{
		var oTag	= m_oConfig.oCurrentNode.getElementsByTagName("SPAN")[0];
		var oLink	= GetChildElement(oTag, "link") ;
		if (null != oLink) 	return ;
	}

	var oTag	= p_oSrcElement.getElementsByTagName("SPAN")[0];
	var oLink	= GetChildElement(oTag, "link") ;
	ShowHideNode(p_oSrcElement) ;
	SelectNode(p_oSrcElement) ;

	if (null != oLink)
	{
		return ;
	}
	else
	{
		oTag		= oTag.parentElement.getElementsByTagName("DIV")[1] ;
		GoFisrtLink(oTag);
	}
}


/************************************************
** GetAllCheckData()
** 功能：获得节点返回值:CheckData
************************************************/

function GetAllCheckData()
{
	var sReturnVal	= "";
	var i ;
	var sTmpVal ;
	var bEnsData ;

	var oCheck	= m_oConfig.oSrcDiv.getElementsByTagName("INPUT") ;
	var nLen	= oCheck.length ;

	for (i=0; i<nLen; i++)
	{
		if (oCheck[i].checked)
		{
			sTmpVal		= oCheck[i].value;
			if (('0' == oCheck[i].read) && ('' != oCheck[i].src) )
			{
				sTmpVal	= GetCheckUrlData(oCheck[i].src);
			}

			bEnsData	= ((m_oConfig.sCheckKey + sReturnVal + m_oConfig.sCheckKey).indexOf((m_oConfig.sCheckKey + sTmpVal + m_oConfig.sCheckKey)) > 0 ) ;

			if (!bEnsData)
			{
				sReturnVal += m_oConfig.sCheckKey + sTmpVal ;
			}
		}
	}
	sReturnVal	= sReturnVal.replace(m_oConfig.sCheckKey, '');
	return sReturnVal ;
}


/************************************************
** SetCheck(p_oSrcElement, p_bAsync)
** 功能：
************************************************/
function SetCheck(p_oSrcElement, p_bAsync)
{
	if (null == p_oSrcElement) return  ;

	SetChildrenCheck(p_oSrcElement, p_bAsync) ;
	SetParentElementCheck(p_oSrcElement) ;
	return  ;
}

/************************************************
** SetParentElementCheck(p_oSrcElement)
** 功能：
************************************************/
function SetParentElementCheck(p_oSrcElement)
{
	var bChecked	= p_oSrcElement.checked ;
	var oTargetNode	= p_oSrcElement.parentElement.parentElement ;

	if (!bChecked)
	{
return;
		var oCheck		= oTargetNode.getElementsByTagName("INPUT") ;
		var nLen		= oCheck.length ;
		var i ;
		for (i=0; i<nLen; i++)
		{
			if (oCheck[i].checked)
			{
				return ;
			}
		}
	}

	var oTargetNode		= GetChildElement(p_oSrcElement.parentElement.parentElement.parentElement, "checkbox") ;

	if (null != oTargetNode)
	{
		oTargetNode.checked	= bChecked ;
		SetParentElementCheck(oTargetNode) ;
	}

	return  ;
}
/************************************************
** SetChildrenCheck(p_oSrcElement, p_bAsync)
** 功能：***
************************************************/
function SetChildrenCheck(p_oSrcElement, p_bAsync)
{
	//return;
	var oContainerElement	= GetChildElement(p_oSrcElement.parentElement, "container") ;
	if (null == oContainerElement) return  ;

	var i ;
	var bChecked	= p_oSrcElement.checked ;
	var oCheck		= oContainerElement.getElementsByTagName("INPUT") ;
	var nLen		= oCheck.length ;

	//alert("p_bAsync:"+p_bAsync+"   bChecked:"+bChecked);
	if ( (p_bAsync) && (bChecked) )
	{
		for (i=0; i<nLen; i++)
		{
			oCheck[i].checked = true;
		}
	}
	else if(!bChecked)
	{
		for (i=0; i<nLen; i++)
		{
			oCheck[i].checked = bChecked;
		}
	}

	return  ;
}


/************************************************
** SetOneCheckData(p_oSrcElement)
** 功能：获得当前节点返回值:CheckData
************************************************/
function SetOneCheckData(p_oSrcElement)
{
	var sReturnVal		= "";
	var sVal			= p_oSrcElement.value ;
	var sCheckDataSrc	= p_oSrcElement.src ;

	if ( (sVal.length < 1) || (!m_oConfig.bCheckDataAsync) )
	{
		sReturnVal	= GetCheckUrlData(sCheckDataSrc) ;

		p_oSrcElement.value	= sReturnVal ;
	}

	return  ;
}

/************************************************
** GetCheckUrlData(p_sURL)
** 功能：获得URL返回值:CheckData
************************************************/
function GetCheckUrlData(p_sURL)
{
	var sReturnVal	= "";
	var oXMLHttp	= new ActiveXObject ("Microsoft.XMLHTTP");
		oXMLHttp.Open("get", p_sURL, false);
		oXMLHttp.Send("");

	sReturnVal	= oXMLHttp.responseText;

	return sReturnVal ;
}

//-------------------------------------------------------------------------------------------------
//					节点事件 (自身状态改变)
//-------------------------------------------------------------------------------------------------

/************************************************
** SelectNode(p_oSrcElement)
************************************************/
function SelectNode(p_oSrcElement)
{
	UpdateMessages(p_oSrcElement) ;
	HighlightNode(p_oSrcElement) ;
	LoadNodeContent(p_oSrcElement) ;
    ScrollIntoView(p_oSrcElement) ;

	if ( (m_oConfig.bUseCheck) && (!m_oConfig.bCheckAsync) )
	{
		var oTargetNode	= GetChildElement(p_oSrcElement, "checkbox") ;
		if (null != oTargetNode)
		{
			oTargetNode.click();
		}
	}
}


/************************************************
** HighlightNode(p_oSrcElement)
************************************************/
function HighlightNode(p_oSrcElement)
{
    var oNewLabel ;
	var oOldLabel ;

	oNewLabel	= GetChildElement(p_oSrcElement, "label") ;

	if (m_oConfig.bChangeLeafImg)
	{
		var cType	= p_oSrcElement.type;

		if (cType == "leaf")
		{
			oImg		= GetChildElement(p_oSrcElement, "img") ;
			oImg.src	= GetImgSrc(oImg.src,3) ;	// 改变图标
		}

		if ( (m_oConfig.oCurrentNode != "moonpiazza") && (p_oSrcElement != m_oConfig.oCurrentNode) )
		{
			if (m_oConfig.oCurrentNode.type == "leaf")
			{
				oImg		= GetChildElement(m_oConfig.oCurrentNode, "img") ;
				oImg.src	= GetImgSrc(oImg.src,0) ;	// 改变图标
			}
		}
	}

	if (oNewLabel != null)
    {
		oNewLabel.className	= m_oConfig.sClsCurrentFocus ;
		oNewLabel.classType	= m_oConfig.sClsCurrentFocus ;

		if ( (m_oConfig.oCurrentNode != "moonpiazza") && (p_oSrcElement != m_oConfig.oCurrentNode) )
		{
				oOldLabel	= GetChildElement(m_oConfig.oCurrentNode, "label") ;

				if (oOldLabel != null)
				{
					oOldLabel.className	= "" ;
					oOldLabel.classType	= "" ;
				}
		}

		m_oConfig.oCurrentNode	= p_oSrcElement ;
	}
}


/************************************************
** UpdateMessages(p_oSrcElement)
************************************************/
function UpdateMessages(p_oSrcElement)
{
    oLabel	= GetChildElement(p_oSrcElement, "label") ;
    if(oLabel != null)
    {
        window.defaultStatus	= oLabel.caption ;
        //top.document.title	= oLabel.title ;
    }
}


/************************************************
** LoadNodeContent(p_oSrcElement)
************************************************/
function LoadNodeContent(p_oSrcElement)
{
    var oLabel ;
    var oLink ;

    oLabel = GetChildElement(p_oSrcElement, "label") ;

    if (oLabel != null)
    {
        oLink	= GetChildElement(oLabel, "link")
        if (oLink != null)
        {

            var sHref		= String(oLink.href) ;

			if(oLink.target)
            {
				GoLink(sHref, oLink.target) ;
            }
        }
    }
}


/************************************************
** ScrollIntoView(p_oSrcElement)
************************************************/
function ScrollIntoView(p_oSrcElement)
{
	if (m_oConfig.bAutoScroll)
	{
		var nY = p_oSrcElement.offsetTop - (window.document.body.clientHeight / 2) ;
		var nX = p_oSrcElement.offsetLeft - 15 ;
		window.scrollTo(nX, nY) ;
	}
}



//-------------------------------------------------------------------------------------------------
//						节点事件 (子孙节点数据读取、显示、隐藏)
//-------------------------------------------------------------------------------------------------

/************************************************
** FoldElseBranch(p_oSrcElement)
************************************************/
function FoldElseBranch(p_oSrcElement)
{
    alert(m_oConfig.oCurrentNode + '==' + p_oSrcElement);
	if (m_oConfig.oCurrentNode == p_oSrcElement)
	{
		return ;
	}

	if (m_oConfig.oCurrentNode != "moonpiazza")
	{
		var bIsAncestorNode		= false;
		var bIsOffspringNode	= false;

			bIsAncestorNode		= IsAncestorNode(m_oConfig.oCurrentNode, p_oSrcElement) ;

		if ( !bIsAncestorNode )
		{
			bIsOffspringNode	= IsAncestorNode(p_oSrcElement, m_oConfig.oCurrentNode) ;

			if ( !bIsOffspringNode )
			{
				FoldOffspringNode(p_oSrcElement.parentElement) ;			// 隐藏子孙节点

				FoldNode(m_oConfig.oCurrentNode) ;							// 隐藏自身

				FoldAncestorNode(p_oSrcElement, m_oConfig.oCurrentNode);		// 隐藏祖先节点
			}
		}


	}
}


/************************************************
** FoldOffspringNode(p_oSrcElement)
************************************************/
function FoldOffspringNode(p_oSrcElement)
{
	var i	= 0 ;
	var oChildren ;
	oChildren	= p_oSrcElement.children ;

	if (oChildren != null)
	{
		for (i=0; i < oChildren.length; i++)
		{
			FoldNode( oChildren[i] ) ;
			if (oChildren[i].type == "branch")
			{
				var oContainerElement = GetChildElement(oChildren[i], "container") ;

				FoldOffspringNode(oContainerElement) ;
			}

		}
	}
}


/*************************************************************************
** FoldAncestorNode(p_oSrcElement, p_oTargetNode)
*************************************************************************/
function FoldAncestorNode(p_oSrcElement, p_oTargetNode)
{
	var oSrcParentNode	= GetParentElement(p_oSrcElement) ;
	var oTargetNode		= GetParentElement(p_oTargetNode) ;

	while (oTargetNode != null)
	{
		if (oTargetNode == oSrcParentNode)
		{
			break ;
		}
		FoldNode(oTargetNode) ;

		oTargetNode	= GetParentElement(oTargetNode) ;
	}
}



/************************************************
** ShowHideNode(p_oSrcElement)
************************************************/
function ShowHideNode(p_oSrcElement)
{

	if (p_oSrcElement.state == "shown")
	{
		FoldNode(p_oSrcElement) ;
	}
	else
	{
		if (!m_oConfig.bShowElseBranch)
		{

			FoldElseBranch(p_oSrcElement) ;
		}

		OutspreadNode(p_oSrcElement) ;
	}

}


/************************************************
** FoldNode(p_oSrcElement)
************************************************/
function FoldNode(p_oSrcElement)
{
	var oContainerElement ;
	var oImg ;

	oContainerElement = GetChildElement(p_oSrcElement, "container") ;

	if (oContainerElement != null)
	{
		oImg	= GetChildElement(p_oSrcElement, "img") ;

		p_oSrcElement.state			= "hidden" ;

		oContainerElement.className	= "hide" ;

		oImg.src	= GetImgSrc(oImg.src,1) ;	// 改变图标


		if (IsAncestorNode(m_oConfig.oCurrentNode, p_oSrcElement)==true)
		{
			SelectNode(p_oSrcElement) ;
		}
	}
}


/************************************************
** IsAncestorNode(p_oSrcElement, p_oTargetNode)
************************************************/
function IsAncestorNode(p_oSrcElement, p_oTargetNode)
{
	var oTmpElement ;
	var bReturnValue	= false ;

	oTmpElement	= GetParentElement(p_oSrcElement) ;

	while (oTmpElement != null)
	{
		if (oTmpElement == p_oTargetNode)
		{
			bReturnValue	= true ;
			break ;
		}

		oTmpElement	= GetParentElement(oTmpElement) ;
	}

	return bReturnValue ;
}


/************************************************
** OutspreadNode(p_oSrcElement)
************************************************/
function OutspreadNode(p_oSrcElement)
{
	var oContainerElement ;
	var oImg ;
	var oLabel ;

	oContainerElement	= GetChildElement(p_oSrcElement, "container") ;

	if (oContainerElement != null)
	{
		oImg	= GetChildElement(p_oSrcElement, "img") ;

		if (oImg != null)
		{
			p_oSrcElement.state			= "shown" ;

			oContainerElement.className	= "shown" ;

			oImg.src	= GetImgSrc(oImg.src,2) ;	// 改变图标
			if (!m_oConfig.bDataAsync)
			{
				var i;
				var oDivContainer	= oContainerElement.getElementsByTagName("Div") ;
				var nLen	= oDivContainer.length;
				for (i=0; i<nLen; i++)
				{
					if ('shown' == oDivContainer[i].state)
					{
						//oDivContainer[i].parentElement.state	= "hidden" ;
						FoldNode(oDivContainer[i]);
					}
				}
			}

			LoadChildren(p_oSrcElement) ;
		}
	}
}


/************************************************
** LoadChildren(p_oSrcElement)
************************************************/
function LoadChildren(p_oSrcElement)
{
    var cXMLSrc ;
    var oLabel ;
    var oContainerElement	= GetChildElement(p_oSrcElement, "container") ;

    if (oContainerElement != null)
    {
		oLabel = GetChildElement(p_oSrcElement, "label")

	    if ( (oContainerElement.children.length == 0) || ( (!m_oConfig.bDataAsync) && (oContainerElement.children.length > 0) ) )
        {
            if (oLabel != null)
            {
                cXMLSrc	= oLabel.xmlsrc ;
                GetXML(cXMLSrc, oContainerElement) ;
				//return true ;
            }
        }

		if (m_oConfig.bUseCheck)
		{
			var oTargetNode	= GetChildElement(p_oSrcElement, "checkbox") ;
			if (null != oTargetNode)
			{
				SetChildrenCheck(oTargetNode, false) ;
			}
		}
    }

    return  ;
}



//-------------------------------------------------------------------------------------------------
//					Mouse 事件 (Click) and (Down, Over, Out)
//-------------------------------------------------------------------------------------------------

/************************************************
** MouseClick(p_oSrcElement)
************************************************/
function MouseClick(p_oSrcElement)
{
	var sSrcElementType	= p_oSrcElement.type ;
	var oTargetNode		= p_oSrcElement.parentElement ;

	if (sSrcElementType == "label")
	{
		ShowHideNode(oTargetNode) ;
		SelectNode(oTargetNode) ;

		if (m_oConfig.bAutoGoFisrtLeaf)
		{
			var	oParentElement		= GetParentElement(p_oSrcElement) ;
			var oContainerElement	= GetChildElement(p_oSrcElement.parentElement, "container") ;
			if (null != oContainerElement)
				GoFisrtLink(oContainerElement.getElementsByTagName("DIV")[0]) ;
		}

		return ;
	}
    else if(sSrcElementType == "img")
    {
		if (oTargetNode.type == "leaf")
		{
			ShowHideNode(oTargetNode) ;
			SelectNode(oTargetNode) ;
		}
		else
		{
			if (m_oConfig.bImgAsync)
			{
				ShowHideNode(oTargetNode) ;
			}
			else
			{
				ShowHideNode(oTargetNode) ;
				SelectNode(oTargetNode) ;
			}

		}

		return ;
	}
    else if(sSrcElementType == "checkbox")
    {
		if (m_oConfig.bCheckShowBranch)
		{
			MouseClick(GetChildElement(p_oSrcElement.parentElement, "label"));
		}

		if (!m_oConfig.bCheckAsync)
		{
			oTargetNode	= p_oSrcElement.parentElement ;
			SelectNode(oTargetNode) ;
		}

		if ("" != p_oSrcElement.src)
		{
			SetOneCheckData(p_oSrcElement);
		}

		SetCheck(p_oSrcElement, true) ;

		return ;
	}
}


/************************************************
** Mouse 事件 (Down, Over, Out)
** NodeMouseDown(p_oSrcElement)
** NodeMouseOver(p_oSrcElement)
** NodeMouseOut(p_oSrcElement)
** ChangeNodeCls(p_oSrcElement, p_sClassName)
************************************************/
function NodeMouseDown(p_oSrcElement)
{
	return ChangeNodeCls(p_oSrcElement, m_oConfig.sClsMouseDown) ;
}

function NodeMouseOver(p_oSrcElement)
{
	return ChangeNodeCls(p_oSrcElement, m_oConfig.sClsMouseOver) ;
}

function NodeMouseOut(p_oSrcElement)
{
	return ChangeNodeCls(p_oSrcElement, "") ;
}

function ChangeNodeCls(p_oSrcElement, p_sClassName)
{
	if (p_sClassName == "")
	{
		p_oSrcElement.className	= p_oSrcElement.classType ;
	}
	else
	{
		p_oSrcElement.className	= p_sClassName ;
	}

	return true ;
}
