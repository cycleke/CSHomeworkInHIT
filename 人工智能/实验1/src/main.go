package main

//Author: Init_new_world 1183710217 吕岸桐

import (
	"fmt"
	"strings"
)

//物品结构
type Object struct {
	Name  string //物品名
	Place string //物品所在地点
}

var (
	//猴子
	Monkey = &Object{
		Name:  "Monkey",
		Place: "A",
	}

	//香蕉
	Banana = &Object{
		Name:  "Banana",
		Place: "Bon",
	}

	//箱子
	Box = &Object{
		Name:  "Box",
		Place: "C",
	}
)

/**
Move:判断某一次移动是否合法
将o移动到place
*/
func Move(o *Object, place string) bool {
	if strings.HasSuffix(place, "on") { //模拟爬上箱子
		if place[0:1] == o.Place[0:1] || (Box.Place[0:1] == place[0:1] || Banana.Place[0:1] == o.Place) {
			return true
		}
		return false
	}
	if strings.HasSuffix(o.Place, "on") { //模拟爬下箱子
		if place[0:1] == o.Place[0:1] || (Box.Place[0:1] == place[0:1] || Banana.Place[0:1] == o.Place) {
			return true
		}
		return false
	}
	if o.Name == "Box" { //猴子推箱子
		if Monkey.Place == o.Place { //猴子和箱子位置一样才能推
			return true
		}
		return false
	}
	if o.Name == "Monkey" { //猴子移动
		if !strings.HasSuffix(o.Place, "on") {
			return true
		}
		return false
	}
	return false
}

//检验选择正确性
func Option(o *Object, place string) bool {
	if Move(o, place) {
		o.Place = place
		return true
	}
	return false
}

//程序运行规则
func Rule() {
	if Monkey.Place != Box.Place {
		if Option(Monkey, Box.Place) {
			fmt.Printf("%v移动到了%v点\n", Monkey.Name, Monkey.Place)
		} else {
			panic(fmt.Errorf("程序错误A"))
		}
	} else if Box.Place != Banana.Place[0:1] {
		if Option(Box, Banana.Place[0:1]) {
			fmt.Printf("%v移动到了%v点\n", Box.Name, Box.Place)
		} else {
			panic(fmt.Errorf("程序错误B"))
		}
	} else if Monkey.Place != Banana.Place {
		if Option(Monkey, Banana.Place) {
			fmt.Printf("%v移动到了%v点\n", Monkey.Name, Monkey.Place)
			fmt.Printf("香蕉拿到了!\n")
		} else {
			panic(fmt.Errorf("程序错误C"))
		}
	}
}

//主过程
func main() {
	for Monkey.Place != Banana.Place {
		Rule()
	}
	if strings.HasSuffix(Monkey.Place, "on") { //猴子最后应该在地面
		if Option(Monkey, Monkey.Place[0:1]) {
			fmt.Printf("%v移动到了%v点\n", Monkey.Name, Monkey.Place)
		} else {
			panic(fmt.Errorf("程序错误C"))
		}
	}
}
