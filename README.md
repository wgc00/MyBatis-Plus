# MyBatis-Plus

## 需要添加的 jar

mybatis plus 插件生成实体

	<mybatis.plus.generator>3.1.2</mybatis.plus.generator>
        <velocity.version>2.1</velocity.version>
        <beetl.version>3.0.10.RELEASE</beetl.version>
	<freemarker.version>2.3.28</freemarker.version>
 	<mysql.version>5.1.47</mysql.version>

	 <!-- 生成器 dao 层加的 jar-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>${mybatis.plus.generator}</version>
        </dependency>
	
        <dependency>
            <groupId>com.ibeetl</groupId>
            <artifactId>beetl</artifactId>
            <version>${beetl.version}</version>
        </dependency>
	<!--  模板 -->
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>${velocity.version}</version>
        </dependency>

        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
	
	<!--  模板 -->
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
        </dependency>
	
	 <!-- 映射到实体的注解架包  entity -->
	<mybatis.plus.annotation.version>3.1.2</mybatis.plus.annotation.version>
        <mybatis.plus.extension.version>3.1.2</mybatis.plus.extension.version>
	<lombok.version>1.18.8</lombok.version>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-annotation</artifactId>
            <version>${mybatis.plus.annotation.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-extension</artifactId>
            <version>${mybatis.plus.extension.version}</version>
            <scope>compile</scope>
        </dependency>
	

	  <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

