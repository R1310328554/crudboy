#!/usr/bin/python
# coding=utf-8

import json # json 数据处理库
import os,re,sys, random, zipfile
from text_util import decomposeLine
from collections import defaultdict

import cobutils
from cobutils.parser import FieldDef
import xml.etree.ElementTree as ET
import xlrd, xlwt


# 1. Try generating with command K on a new line. Ask for a pytorch script of a feedforward neural network
# 2. Then, select the outputted code and hit chat. Ask if there's a bug. Ask how to improve.
# 3. Try selecting some code and hitting edit. Ask the bot to add residual layers.
# 4. To try out cursor on your own projects, go to the file menu (top left) and open a folder.

import cobutils
from cobutils.parser import FieldDef


cobolRoot = cobutils.RecordDef('rx', remove_suffix=True)


def parse_xml(xml_path):
    fields = []
    tree = ET.parse(xml_path)
    root = tree.getroot()
    fields = []
    
    for child in root:
        # print(child.tag, child.attrib) 
        if child.tag == '{http://maven.apache.org/POM/4.0.0}dependencies':
            for child2 in child:
                if child2.tag == '{http://maven.apache.org/POM/4.0.0}dependency':
                    # print(child2.tag, child2.text) 
                    f = '<dependency>\n'
                    for child3 in child2:
                        # print(child3.tag, child3.attrib, child3.text)
                        if child3.tag == '{http://maven.apache.org/POM/4.0.0}groupId':
                            # print('child3.text', child3.text)
                            f = f + '<groupId>' + child3.text + "" + '</groupId>'
                        if child3.tag == '{http://maven.apache.org/POM/4.0.0}artifactId':
                            # print('child3.text', child3.text)
                            if child3.text.startswith('yudao-'):
                                f = ''
                                break
                            f = f+ '\n<artifactId>' + child3.text + '</artifactId>'
                        if child3.tag == '{http://maven.apache.org/POM/4.0.0}version':
                            # print('child3.text', child3.text)
                            f = f+ '\n<version>' + child3.text + '</version>'
                            pass
                    if f:
                        # print(f)
                        fields.append(f + '\n</dependency>\n')
    return fields
    
    root.get('project')
    
    allDeps = tree.findall('dependencies/dependency')
    for dep in allDeps:
        print(dep)
        
    return ''
    
    
    
def parse_xml_roots(mavenProjects, discard=False):
    fields = []
    for prj in mavenProjects:
        fields.extend(parse_xml_root(prj))
    print('---------- ')
    print(len( fields))
    ret = set(fields)
    print(len( ret ))
    
    if discard:
        pom_xml2 = r'D:\d\git\crudboy\pom.xml'
        fields2 = parse_xml(pom_xml2)
        for f2 in fields2:
            #   ret.difference_update
            # ret.remove(f2)
            ret.discard(f2)
    
    
    print(len( ret ))
    for f in ret:
        print(f)
    
def parse_xml_root(srcDir):
    fields = []
    # os.listdir(srcDir)
    for root, dirs, files in os.walk(srcDir):
        for dir in dirs:
            if dir == 'target':
                # 希望 os.walk(srcDir) 的时候能够过滤掉这些 target目录， 
                # continue
                pass
        
        if os.path.split(root)[1] == ('target'):
            # 希望 os.walk(srcDir) 的时候能够过滤掉这些 target目录， 
            continue
        
        for file in files:
            if file.endswith("pom.xml"):
                file_path = os.path.join(root, file)
                # fpath = file_path.replace(srcDir, outDir)
                # od = os.path.split(fpath)[0]
                print(file_path)
                if file_path != r'D:\d\git\ruoyi-vue-pro\yudao-server\pom.xml':
                    # continue
                    pass
                deps = parse_xml(file_path)
                if deps:
                    fields.extend(deps)
                    # break
    return fields

#读取模板
xml_path = r"D:\d\git\ruoyi-vue-pro"
# parse_xml_root(xml_path)
mavenProjects = r'D:\d\git\ruoyi-vue-pro\yudao-dependencies D:\d\git\ruoyi-vue-pro\yudao-example D:\d\git\ruoyi-vue-pro\yudao-framework D:\d\git\ruoyi-vue-pro\yudao-module-bpm D:\d\git\ruoyi-vue-pro\yudao-module-infra D:\d\git\ruoyi-vue-pro\yudao-module-mall D:\d\git\ruoyi-vue-pro\yudao-module-member D:\d\git\ruoyi-vue-pro\yudao-module-mp D:\d\git\ruoyi-vue-pro\yudao-module-pay D:\d\git\ruoyi-vue-pro\yudao-module-report D:\d\git\ruoyi-vue-pro\yudao-module-system D:\d\git\ruoyi-vue-pro\yudao-module-visualization D:\d\git\ruoyi-vue-pro\yudao-module-xg D:\d\git\ruoyi-vue-pro\yudao-module-yjgl D:\d\git\ruoyi-vue-pro\yudao-server'.split()
mavenProjects = r'D:\d\git\ruoyi-vue-pro\yudao-module-mall D:\d\git\ruoyi-vue-pro\yudao-module-member D:\d\git\ruoyi-vue-pro\yudao-module-mp D:\d\git\ruoyi-vue-pro\yudao-module-pay D:\d\git\ruoyi-vue-pro\yudao-module-report D:\d\git\ruoyi-vue-pro\yudao-module-visualization  D:\d\git\ruoyi-vue-pro\yudao-server'.split()
parse_xml_roots(mavenProjects)


pom_xml = r"D:\d\git\ruoyi-vue-pro\yudao-example\yudao-sso-demo-by-code\pom.xml"

def www():
    from xml.dom import minidom #导入模块
    # from xml.dom import 
    dom=minidom.parse(pom_xml) #打开xml
    # names=dom.getElementsByTagName("name") #获取节点列表
    ds = dom.getElementsByTagName("dependency")
    # cs = dom.childNodes
    for c in ds:
        # isinstance(c, minidom.Element)
        c:minidom.Element
        dependency_text = ET.tostring(c, encoding='unicode')
        print(dependency_text)
        for cc in c.childNodes:
            print(c.childNodes)
        
# for i in range(len(names)):
    # print(names[i].firstChild.data)   #打印节点数据

# www()


def asdfaf():
    tree = ET.parse(pom_xml)
    root = tree.getroot()
    # # 查找所有的dependency节点
    dependencies = root.findall('.//dependency')

    # 遍历dependency节点并打印内容
    for dependency in dependencies:
        # 获取dependency节点的文本内容
        dependency_text = ET.tostring(dependency, encoding='unicode')
        print(dependency_text)
    

def asdf():
    # 加载pom.xml文件
    tree = ET.parse('pom.xml')
    root = tree.getroot()

    # 查找所有的dependency节点
    dependencies = root.findall('.//dependency')

    # 遍历dependency节点并打印内容
    for dependency in dependencies:
        # 获取dependency节点的子节点内容
        group_id = dependency.find('groupId').text
        artifact_id = dependency.find('artifactId').text
        version = dependency.find('version').text

        print("Dependency:")
        print("  groupId:", group_id)
        print("  artifactId:", artifact_id)
        print("  version:", version)
        print()