<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/TR/WD-xsl">

<xsl:template match="/">
	<xsl:eval>
		var LeafImgSrc		= 'images/NodeImg0.gif' ;
		var BranchImgSrc	= 'images/NodeImg1.gif' ;
	</xsl:eval>
	<xsl:apply-templates select="/*/TreeNode"/>
</xsl:template>

<xsl:template match="TreeNode">

	<div class="clsItem" type="leaf" valign="top" >
		<img type="img" onclick="MouseClick(this)">
			<xsl:attribute name="src">
				<xsl:choose>
					<xsl:when test="@NodeImgSrc">
						<xsl:value-of select="@NodeImgSrc" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:eval>
							LeafImgSrc	;
						</xsl:eval>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
		</img><xsl:choose>
			<xsl:when test="@CheckData">
				<xsl:element name="input">
					<xsl:attribute name="type">checkbox</xsl:attribute>
					<xsl:attribute name="read">1</xsl:attribute>
					<xsl:attribute name="name">CheckData</xsl:attribute>
					<xsl:attribute name="onclick">MouseClick(this)</xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="@CheckData" /></xsl:attribute>
				</xsl:element>
			</xsl:when>
			<xsl:when test="@CheckDataSrc">
				<xsl:element name="input">
					<xsl:attribute name="type">checkbox</xsl:attribute>
					<xsl:attribute name="read">0</xsl:attribute>
					<xsl:attribute name="name">CheckData</xsl:attribute>
					<xsl:attribute name="value"></xsl:attribute>
					<xsl:attribute name="onclick">MouseClick(this)</xsl:attribute>
					<xsl:attribute name="src"><xsl:value-of select="@CheckDataSrc" /></xsl:attribute>
				</xsl:element>
			</xsl:when>
		</xsl:choose>
		<span class="clsLabel" type="label" onclick="MouseClick(this)" onmousedown="NodeMouseDown(this);" onmouseover="NodeMouseOver(this);" onmouseout="NodeMouseOut(this);" >
			<xsl:choose>
				<xsl:when test="@CheckData">
					<xsl:attribute name="style">position:relative; left:-7px;;top:5px;</xsl:attribute>
				</xsl:when>
				<xsl:when test="@CheckDataSrc">
					<xsl:attribute name="style">position:relative; left:-7px;;top:5px;</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="style">position:relative; left:3px;;top:5px;</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>

			<xsl:attribute name="title">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@Title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:attribute name="caption">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@Title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:choose>
				<xsl:when test="@Href">
					<span type="link" class="Link">
						<xsl:choose>
							<xsl:when test="@Target">
								<xsl:attribute name="target"><xsl:value-of select="@Target" /></xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="target">moonpiazza</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:attribute name="href">
							<xsl:value-of select="@Href" />
						</xsl:attribute>
						<xsl:value-of select="@Title" />
					</span>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="@Title" />
				</xsl:otherwise>
			</xsl:choose>
		</span>
	</div>
</xsl:template>

<xsl:template match="TreeNode[* or @NodeXMLSrc]">

	<div class="clsItem" type="branch">
		<img type="img" onclick="MouseClick(this)">
			<xsl:attribute name="src">
				<xsl:choose>
					<xsl:when test="@NodeImgSrc">
						<xsl:value-of select="@NodeImgSrc" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:eval>
							BranchImgSrc	;
						</xsl:eval>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
		</img><xsl:choose>
			<xsl:when test="@CheckData">
				<xsl:element name="input">
					<xsl:attribute name="type">checkbox</xsl:attribute>
					<xsl:attribute name="read">1</xsl:attribute>
					<xsl:attribute name="name">CheckData</xsl:attribute>
					<xsl:attribute name="onclick">MouseClick(this)</xsl:attribute>
					<xsl:attribute name="value">
						<xsl:value-of select="@CheckData" />
					</xsl:attribute>
				</xsl:element>
			</xsl:when>
			<xsl:when test="@CheckDataSrc">
				<xsl:element name="input">
					<xsl:attribute name="type">checkbox</xsl:attribute>
					<xsl:attribute name="read">0</xsl:attribute>
					<xsl:attribute name="name">CheckData</xsl:attribute>
					<xsl:attribute name="value"></xsl:attribute>
					<xsl:attribute name="onclick">MouseClick(this)</xsl:attribute>
					<xsl:attribute name="src">
						<xsl:value-of select="@CheckDataSrc" />
					</xsl:attribute>
				</xsl:element>
			</xsl:when>
		</xsl:choose>
		<span class="clsLabel" type="label" onclick="MouseClick(this)" onmousedown="NodeMouseDown(this);" onmouseover="NodeMouseOver(this);" onmouseout="NodeMouseOut(this);" >
			<xsl:choose>
				<xsl:when test="@CheckData">
					<xsl:attribute name="style">position:relative; left:-7px;top:5px;</xsl:attribute>
				</xsl:when>
				<xsl:when test="@CheckDataSrc">
					<xsl:attribute name="style">position:relative; left:-7px;top:5px;</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="style">position:relative; left:3px;top:5px;</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>

			<xsl:attribute name="xmlsrc">
				<xsl:value-of select="@NodeXMLSrc" />
			</xsl:attribute>
			<xsl:attribute name="title">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@Title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:attribute name="caption">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@Title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:choose>
				<xsl:when test="@Href">
					<span type="link" class="Link">
						<xsl:choose>
							<xsl:when test="@Target">
								<xsl:attribute name="target"><xsl:value-of select="@Target" /></xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="target">moonpiazza</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:attribute name="href">
							<xsl:value-of select="@Href" />
						</xsl:attribute>
						<xsl:value-of select="@Title" />
					</span>
				</xsl:when>
				<xsl:otherwise>
					<span class="Link">
						<xsl:value-of select="@Title" />
					</span>
				</xsl:otherwise>
			</xsl:choose>
		</span>

		<div class="hide" type="container">
			<xsl:apply-templates />
		</div>
	</div>
</xsl:template>

</xsl:stylesheet>
