<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:variable name="LeafImgSrc">./img/NodeImg0.gif</xsl:variable>
<xsl:variable name="BranchImgSrc">./img/NodeImg1.gif</xsl:variable>

<xsl:template match="/">
	<xsl:apply-templates />
</xsl:template>

<xsl:template match="TreeNode">
	<xsl:variable name="title">
		<xsl:value-of select="@Title" />
	</xsl:variable>
	<xsl:choose>
		<xsl:when test="@NodeImgSrc">
			<xsl:variable name="LeafImgSrc">
				<xsl:value-of select="@NodeImgSrc" />
			</xsl:variable>
		</xsl:when>
	</xsl:choose>

	<div class="clsItem" type="leaf">
		<img type="img" onclick="MouseClick(this)">
			<xsl:attribute name="src">
				<xsl:choose>
					<xsl:when test="@NodeImgSrc">
						<xsl:value-of select="@NodeImgSrc" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$LeafImgSrc" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
		</img>

		<xsl:call-template name="Check" />

		<span class="clsLabel" type="label" onclick="MouseClick(this)" onmousedown="NodeMouseDown(this);" onmouseover="NodeMouseOver(this);" onmouseout="NodeMouseOut(this);" >
			<xsl:choose>
				<xsl:when test="@CheckData or @CheckDataSrc">
					<xsl:attribute name="style">position:relative; left:-1px;top:0px;</xsl:attribute>
				</xsl:when>

				<xsl:otherwise>
					<xsl:attribute name="style">position:relative; left:3px;top:0px;</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>

			<xsl:attribute name="title">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:attribute name="caption">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:choose>
				<xsl:when test="@Href">
					<span type="link" class="Link">
						<xsl:choose>
							<xsl:when test="@Target">
								<xsl:attribute name="target">
									<xsl:value-of select="@Target" />
								</xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="target">moonpiazza</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:attribute name="href">
							<xsl:value-of select="@Href" />
						</xsl:attribute>
						<xsl:value-of select="$title" disable-output-escaping="yes" />
					</span>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$title" disable-output-escaping="yes" />
				</xsl:otherwise>
			</xsl:choose>
		</span>
	</div>
</xsl:template>

<xsl:template match="TreeNode[* or @NodeXMLSrc]">
	<xsl:variable name="title">
		<xsl:value-of select="@Title" />
	</xsl:variable>


	<div class="clsItem" type="branch">
		<img type="img" onclick="MouseClick(this)">
			<xsl:attribute name="src">
				<xsl:choose>
					<xsl:when test="@NodeImgSrc">
						<xsl:value-of select="@NodeImgSrc" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$BranchImgSrc" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
		</img>

		<xsl:call-template name="Check" />

		<span class="clsLabel" type="label" onclick="MouseClick(this)" onmousedown="NodeMouseDown(this);" onmouseover="NodeMouseOver(this);" onmouseout="NodeMouseOut(this);" >
			<xsl:choose>
				<xsl:when test="@CheckData or @CheckDataSrc">
					<xsl:attribute name="style">position:relative; left:-1px;top:0px;</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="style">position:relative; left:3px;top:0px;</xsl:attribute>
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
						<xsl:value-of select="$title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:attribute name="caption">
				<xsl:choose>
					<xsl:when test="@Caption">
						<xsl:value-of select="@Caption" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$title" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:choose>
				<xsl:when test="@Href">
					<span type="link" class="Link">
						<xsl:choose>
							<xsl:when test="@Target">
								<xsl:attribute name="target">
									<xsl:value-of select="@Target" />
								</xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="target">moonpiazza</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:attribute name="href">
							<xsl:value-of select="@Href" />
						</xsl:attribute>
						<xsl:value-of select="$title" disable-output-escaping="yes" />
					</span>
				</xsl:when>
				<xsl:otherwise>
					<span class="Link">
						<xsl:value-of select="$title" disable-output-escaping="yes" />
					</span>
				</xsl:otherwise>
			</xsl:choose>
		</span>

		<div class="hide" type="container">
			<xsl:apply-templates />
		</div>
	</div>
</xsl:template>

<xsl:template name="Check">
	<xsl:choose>
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
                <xsl:if test="@Checked[.='ok']">
                	<xsl:attribute name="checked"></xsl:attribute>
                </xsl:if>
				<xsl:attribute name="onclick">MouseClick(this)</xsl:attribute>
				<xsl:attribute name="src">
					<xsl:value-of select="@CheckDataSrc" />
				</xsl:attribute>
			</xsl:element>
		</xsl:when>
	</xsl:choose>
</xsl:template>

</xsl:stylesheet>
