	
// 城市选择框显示
var inCity = new Object();
inCity.cssParents = function(){ //城市显示
    $(inCity.id).css({"width":inCity.width+"px","height":inCity.height+"px"});
}
inCity.place = function (e) {
    e.click(function(){
        placeThis = $(this);
        //城市显示
        inCity.cssParents(); 
        $(inCity.id).show();
        return false;
    })
}
inCity.destination = function (e){
    e.click(function(){
        placeThis = $(this);
        //城市显示
        inCity.cssParents();  
        $(inCity.id).show();
        return false;
    })
}

/*******************************************************************/
/*function toHotel(){
	var cityT=$("#place").attr("value");
    $.post("/hotel",{city:cityT});
}
*/
$(document).ready(function(){
	$("#hotel").click(function(){
		$.post("/hotel",{
			city:cityT
		},
		function(data,status){
			alert("数据: \n" + data + "\n状态: " + status);
		});
	});
});

/* 城市HTML模板 */
inCity._template = [
    '<h5>请选择城市</h5>',
    '<div class="screen">',
        '<a href="javascript:void(0)" class="shar">热门</a>',
        '<a href="javascript:void(0)">ABCDEFG</a>',
        '<a href="javascript:void(0)">HIJKL</a>',
        '<a href="javascript:void(0)">MNOPQRST</a>',
        '<a href="javascript:void(0)">WXYZ</a>',
    '</div>',
    '<div class="city_pos">',
        '<div class="city_a_le1">',
        '</div>',
        '<div class="city_a_le1" style="display: none">',
        '</div>',
        '<div class="city_a_le1" style="display: none">',
        '</div>',
        '<div class="city_a_le1" style="display: none">',
        '</div>',
        '<div class="city_a_le1" style="display: none">',
        '</div>',
    '</div>'
];
/* 所有城市数据,可以按照格式自行添加（北京|beijing|bj），前16条为热门城市 */

inCity.allCity = ['北京|beijing|bj','上海|shanghai|sh', '重庆|chongqing|cq',  '深圳|shenzhen|sz', '广州|guangzhou|gz', '杭州|hangzhou|hz',
    '南京|nanjing|nj', '苏州|shuzhou|sz', '天津|tianjin|tj', '成都|chengdu|cd', '南昌|nanchang|nc', '三亚|sanya|sy','青岛|qingdao|qd',
    '厦门|xiamen|xm', '西安|xian|xa','长沙|changsha|cs','合肥|hefei|hf','西藏|xizang|xz', '内蒙古|neimenggu|nmg', '安庆|anqing|aq', '阿勒泰|ataile|atl', '安康|ankang|ak',
    '阿克苏|akesu|aks', '亳州|bozhou|bz','包头|baotou|bt', '北海|beihai|bh', '百色|baise|bs','保山|baoshan|bs', '长治|changzhi|cz', '长春|changchun|cc', '常州|changzhou|cz', 
    '郴州|chenzhou|cz', '承德|chengde|cd',  '赤峰|chifeng|cf', '大同|datong|dt', '大连|dalian|dl',  '东营|dongying|dy', '大庆|daqing|dq', '丹东|dandong|dd',
    '东莞|dongguan|dg', '达州|dazhou|dz','德阳|deyang|dy', '鄂州|ezhou|ez', '恩施|enshi|es',
    '福州|fuzhou|fz', '阜阳|fuyang|fy','佛山|foshan|fs','抚顺|fushun|fs', '贵阳|guiyang|gy',
    '桂林|guilin|gl', '广元|guangyuan|gy',  '呼和浩特|huhehaote|hhht', 
    '黑河|heihe|hh',  '哈尔滨|haerbin|heb', '海口|haikou|hk', '黄山|huangshan|hs', '邯郸|handan|hd',
    '汉中|hanzhong|hz', '和田|hetian|ht', '晋城|jincheng|jc', '锦州|jinzhou|jz', 
    '济宁|jining|jn', '九江|jiujiang|jj', '佳木斯|jiamusi|jms', '济南|jinan|jn',
    '喀什|kashi|ks', '昆明|kunming|km', '开封|kaifeng|kf', '克拉玛依|kelamayi|klmy','克孜勒苏|Kezilesu|kzls', '兰州|lanzhou|lz',
    '洛阳|luoyang|ly', '丽江|lijiang|lj', '林芝|linzhi|lz', '柳州|liuzhou|lz', '泸州|luzhou|lz', '连云港|lianyungang|lyg', 
    '拉萨|lasa|ls', '临沧|lincang|lc', '临沂|linyi|ly', '牡丹江|mudanjiang|mdj',  '绵阳|mianyang|my',
    '梅州|meizhou|mz', '茂名|maoming|mm', '南充|nanchong|nc', '南宁|nanning|nn', '南阳|nanyang|ny', '南通|nantong|nt', '那区|naqu|nq',
    '宁波|ningbo|nb', '攀枝花|panzhihua|pzh', '盘锦|panjin|pj', '莆田|putian|pt', '衢州|quzhou|qz', '秦皇岛|qinhuangdao|qhd', '庆阳|qingyang|qy', '齐齐哈尔|qiqihaer|qqhe',
    '石家庄|shijiazhuang|sjz',  '沈阳|shenyang|sy',  '铜仁|tongren|tr',  
    '通辽|tongliao|tl', '太原|taiyuan|ty', '威海|weihai|wh', '梧州|wuzhou|wz', '文山|wenshan|ws', '无锡|wuxi|wx', '潍坊|weifang|wf',  '芜湖|wuhu|wh',
    '温州|wenzhou|wz', '乌鲁木齐|wulumuqi|wlmq',  '乌海|wuhai|wh',   '襄阳|xiangyang|xy',
    '西宁|xining|xn', '徐州|xuzhou|xz',   '延安|yanan|ya', '运城|yuncheng|yc',
    '烟台|yantai|yt', '银川|yinchuan|yc', '宜昌|yichang|yc', '宜宾|yibin|yb', '盐城|yancheng|yc', '延吉|yanji|yj', '玉树|yushu|ys',  '珠海|zhuhai|zh', '昭通|zhaotong|zt',
    '张家界|zhangjiajie|zjj', '舟山|zhoushan|zs', '郑州|zhengzhou|zz', '中卫|zhongwei|zw',  '湛江|zhanjiang|zj'];

/* 正则表达式 筛选中文城市名、拼音、首字母 */

inCity.regEx = /^([\u4E00-\u9FA5\uf900-\ufa2d]+)\|(\w+)\|(\w)\w*$/i;
inCity.regExChiese = /([\u4E00-\u9FA5\uf900-\ufa2d]+)/;

(function () {
    var citys = inCity.allCity, match, letter,
            regEx = inCity.regEx,
            reg2 = /^[a-g]$/i, reg3 = /^[h-l]$/i, reg4 = /^[m-t]$/i, reg5 = /^[w-z]$/i;
    if (!inCity.oCity) {
        inCity.oCity = {hot:{},ABCDEFG:{}, HIJKL:{}, MNOPQRST:{}, WXYZ:{}};
        //console.log(citys.length);
        for (var i = 0, n = citys.length; i < n; i++) {
            match = regEx.exec(citys[i]); //exec
            letter = match[3].toUpperCase(); //转换字母为大写

            if (reg2.test(letter)) { //test检测一个字符串是否匹配某个模式
                if (!inCity.oCity.ABCDEFG[letter]) inCity.oCity.ABCDEFG[letter] = [];
                inCity.oCity.ABCDEFG[letter].push(match[1]);
            } else if (reg3.test(letter)) {
                if (!inCity.oCity.HIJKL[letter]) inCity.oCity.HIJKL[letter] = [];
                inCity.oCity.HIJKL[letter].push(match[1]);
            } else if (reg4.test(letter)) {
                if (!inCity.oCity.MNOPQRST[letter]) inCity.oCity.MNOPQRST[letter] = [];
                inCity.oCity.MNOPQRST[letter].push(match[1]);
            } else if (reg5.test(letter)) {
                if (!inCity.oCity.WXYZ[letter]) inCity.oCity.WXYZ[letter] = [];
                inCity.oCity.WXYZ[letter].push(match[1]);
            }
            /* 热门城市 前16条 */
            if(i<16){
                if(!inCity.oCity.hot['hot']) inCity.oCity.hot['hot'] = [];
                inCity.oCity.hot['hot'].push(match[1]);
            }
        }
    }
})();
// 热门城市
inCity.Hot = function(cityA){
    var ckey,odda,sortKey,str,odda=[],abc=[],key,regEx = inCity.regEx,oCity = inCity.oCity,len,leni;
    for(key in oCity){
        sortKey=[];
        for(ckey in oCity[key]){
            sortKey.push(ckey);
            // ckey按照ABCDEDG顺序排序
            sortKey.sort();
        }
        for(var j=0,k = sortKey.length;j<k;j++){
            odda = [];
            abc=[];
            for(var i=0,n=oCity[key][sortKey[j]].length;i<n;i++){
                
                if(key == 'hot'){
                   $(inCity.id).find(inCity.Children).eq(0).append('<a href="javascript:void(0)">' + oCity[key][sortKey[j]][i] + '</a>');
                    odda.push(str);
                }else{
                    str = '<a href="javascript:void(0)">' + oCity[key][sortKey[j]][i] + '</a>';
                    inCity.arrRepeat(abc,sortKey,j); //获取字母
                    odda.push(str);
                    len = n;
                    leni = i;
                }
                
            }
            inCity.cityPinyin(leni,len,key,abc,odda);
        }
    }
}

// 按拼音排序
inCity.cityPinyin = function(leni,len,key,abc,odda){
    if(leni != undefined && key != 'hot'){
        if(len-1 == leni){
            var one;
            switch (key)
            {
                case 'ABCDEFG':
                  one = 1;
                  break;
                case 'HIJKL':
                  one = 2;
                  break;
                case 'MNOPQRST':
                  one = 3;
                  break;
                case 'WXYZ':
                  one = 4;
                  break;
            }
            $(inCity.id).find(inCity.Children).eq(one).append('<div class="Letter">'
                +'<span>'+abc[0]+'</span>'
                +'<div>'
                    +odda.join('')
                +'</div>'
            +'</div>');
        }
    }
}

// 数组去重
inCity.arrRepeat = function(abc,sortKey,j){
    var nab = sortKey[j];
    for(var i in abc){
        if(abc[i] == nab){
            return nab = 1;
        }
    }
    if(nab != 1){
        abc.push(sortKey[j])
    }
}
// 城市切换
inCity.payment = function($this){
    var ind = $this.index();
    $this.siblings().removeClass("shar");
    $this.addClass("shar");
    $this.parent().next().children().hide();
    $this.parent().next().children().eq(ind).show();
}
// 给input赋值
inCity.cityClick = function ($this) {
    $(".city_a_le1 a").click(function () {
        var a_city = $(this).text();  //当前选择的城市
        $(inCity.id).hide();  //隐藏城市选择框 
        placeThis.val(a_city);  //赋值
        return false;
    })
}
//*******************************************************************************************
var cityA = $(".city_a_le1 a"); //城市
var pla = $("#place");  //出发地
var dest = $("#destination");  //目的地
// 默认值
inCity.width = "345";  //城市选择框  宽
inCity.height = "auto";  //城市选择框  高
inCity.id = "#in_city";  //城市选择框  父级ID
inCity.Children = '.city_a_le1';  //城市名box
// 初始化 城市HTML模板
$(inCity.id).prepend(inCity._template.join(''));
inCity.Hot(cityA);
//城市 导航
var apay = $(".screen a");
var placeThis; //当前选择标签
apay.click(function(obj){  //城市导航
	inCity.payment($(this));
})
inCity.place(pla); //出发地
inCity.destination(dest);  //目的地
inCity.cityClick(cityA); //显示赋值城市
//*******************************************************************************************
	var hw_selecttext ="北京|北京*上海|上海*天津|天津*重庆|重庆*河北|石家庄^邯郸^邢台^保定^张家口^承德^廊坊^唐山^秦皇岛^沧州^衡水*山西|太原^大同^阳泉^长治^晋城^朔州^吕梁^忻州^晋中^临汾^运城*内蒙古|呼和浩特^包头^乌海^赤峰^呼伦贝尔盟^阿拉善盟^哲里木盟^兴安盟^乌兰察布盟^锡林郭勒盟^巴彦淖尔盟^伊克昭盟*辽宁|沈阳^大连^鞍山^抚顺^本溪^丹东^锦州^营口^阜新^辽阳^盘锦^铁岭^朝阳^葫芦岛*吉林|长春^吉林^四平^辽源^通化^白山^松原^白城^延边*黑龙江|哈尔滨^齐齐哈尔^牡丹江^佳木斯^大庆^绥化^鹤岗^鸡西^黑河^双鸭山^伊春^七台河^大兴安岭*江苏|南京^镇江^苏州^南通^扬州^盐城^徐州^连云港^常州^无锡^宿迁^泰州^淮安*浙江|杭州^宁波^温州^嘉兴^湖州^绍兴^金华^衢州^舟山^台州^丽水*安徽|合肥^芜湖^蚌埠^马鞍山^淮北^铜陵^安庆^黄山^滁州^宿州^池州^淮南^巢湖^阜阳^六安^宣城^亳州*福建|福州^厦门^莆田^三明^泉州^漳州^南平^龙岩^宁德*江西|南昌市^景德镇^九江^鹰潭^萍乡^新馀^赣州^吉安^宜春^抚州^上饶*山东|济南^青岛^淄博^枣庄^东营^烟台^潍坊^济宁^泰安^威海^日照^莱芜^临沂^德州^聊城^滨州^菏泽*河南|郑州^开封^洛阳^平顶山^安阳^鹤壁^新乡^焦作^濮阳^许昌^漯河^三门峡^南阳^商丘^信阳^周口^驻马店^济源*湖北|武汉^宜昌^荆州^襄樊^黄石^荆门^黄冈^十堰^恩施^潜江^天门^仙桃^随州^咸宁^孝感^鄂州*湖南|长沙^常德^株洲^湘潭^衡阳^岳阳^邵阳^益阳^娄底^怀化^郴州^永州^湘西^张家界*广东|广州^深圳^珠海^汕头^东莞^中山^佛山^韶关^江门^湛江^茂名^肇庆^惠州^梅州^汕尾^河源^阳江^清远^潮州^揭阳^云浮*广西|南宁^柳州^桂林^梧州^北海^防城港^钦州^贵港^玉林^南宁地区^柳州地区^贺州^百色^河池*海南|海口^三亚*四川|成都^绵阳^德阳^自贡^攀枝花^广元^内江^乐山^南充^宜宾^广安^达川^雅安^眉山^甘孜^凉山^泸州*贵州|贵阳^六盘水^遵义^安顺^铜仁^黔西南^毕节^黔东南^黔南*云南|昆明^大理^曲靖^玉溪^昭通^楚雄^红河^文山^思茅^西双版纳^保山^德宏^丽江^怒江^迪庆^临沧*西藏|拉萨^日喀则^山南^林芝^昌都^阿里^那曲*陕西|西安^宝鸡^咸阳^铜川^渭南^延安^榆林^汉中^安康^商洛*甘肃|兰州^嘉峪关^金昌^白银^天水^酒泉^张掖^武威^定西^陇南^平凉^庆阳^临夏^甘南*宁夏|银川^石嘴山^吴忠^固原*青海|西宁^海东^海南^海北^黄南^玉树^果洛^海西*新疆|乌鲁木齐^石河子^克拉玛依^伊犁^巴音郭勒^昌吉^克孜勒苏柯尔克孜^博尔塔拉^吐鲁番^哈密^喀什^和田^阿克苏*香港|香港*澳门|澳门*台湾|台北^高雄^台中^台南^屏东^南投^云林^新竹^彰化^苗栗^嘉义^花莲^桃园^宜兰^基隆^台东^金门^马祖^澎湖*其它|"
	var TheSplit1 = "*"  
	var TheSplit2 = "|" 
	var TheSplit3 = "^"  
	var TheSplit4 = "@"  
	var hwallselecttext = hw_selecttext
	var hwdefault_value = ""
	var hwallselecttextarr
	hwallselecttextarr = hwallselecttext.split(TheSplit1)
	hwArraylength = hwallselecttextarr.length
	var hwwhere = new Array(hwArraylength);
	hwwhere[0]= new hw_comefrom("请选择@","请选择@");
	
	for (var hwl=0;hwl<hwArraylength;hwl++)
	{
	 eval(hwwhere[hwl+1] = new hw_comefrom(hwallselecttextarr[hwl].split(TheSplit2)[0],hwallselecttextarr[hwl].split(TheSplit2)[1]))
	}
	function hw_comefrom(hwSelect_s1,hwSelect_s2) { this.hwSelect_s1 = hwSelect_s1; this.hwSelect_s2 = hwSelect_s2; } 
	
	function hw_select()
	{
	 with(document.all.hw_1)
	 {
	  var hwSelect_s12 = options[selectedIndex].value;
	 }
	 for(hwi = 0;hwi < hwwhere.length;hwi ++)
	 {
	  if (hwwhere[hwi].hwSelect_s1.indexOf(TheSplit4)!=-1)
	  {
	   var hwThisV = hwwhere[hwi].hwSelect_s1.split(TheSplit4)[1]
	  }
	  else
	  {
	   var hwThisV = hwwhere[hwi].hwSelect_s1
	  }
	  if (hwThisV == hwSelect_s12)
	  {
	   hwSelect_s13 = (hwwhere[hwi].hwSelect_s2).split(TheSplit3);
	   for(hwj = 0;hwj < hwSelect_s13.length;hwj++)
	   {
	    with(document.all.hw_2)
	    {
	     length = hwSelect_s13.length;
	     if (hwSelect_s13[hwj].indexOf(TheSplit4)!=-1)
	     {
	      options[hwj].text = hwSelect_s13[hwj].split(TheSplit4)[0]
	      options[hwj].value = hwSelect_s13[hwj].split(TheSplit4)[1]
	     }
	     else
	     {
	      options[hwj].text = hwSelect_s13[hwj];
	      options[hwj].value = hwSelect_s13[hwj];
	     }
	     var hwSelect_s14=options[selectedIndex].value;
	    }
	   }
	  break;
	  }
	 }
	 document.all.hw.value=hwSelect_s14;
	}
	function hw_init()
	{
	 with(document.all.hw_1)
	 {
	  length = hwwhere.length;
	  var hwm = 0
	  for(hwk=0;hwk<hwwhere.length;hwk++)
	  {
	   if (hwwhere[hwk].hwSelect_s1.indexOf(TheSplit4)!=-1)
	   {
	    options[hwk].text = hwwhere[hwk].hwSelect_s1.split(TheSplit4)[0];
	    options[hwk].value = hwwhere[hwk].hwSelect_s1.split(TheSplit4)[1];
	    if (hwdefault_value.indexOf(hwwhere[hwk].hwSelect_s1.split(TheSplit4)[1])!=-1){hwm = hwk}
	   }
	   else
	   {
	    options[hwk].text = hwwhere[hwk].hwSelect_s1;
	    options[hwk].value = hwwhere[hwk].hwSelect_s1;
	    if (hwdefault_value.indexOf(hwwhere[hwk].hwSelect_s1)!=-1){hwm = hwk}
	   }
	  }
	  selectedIndex = hwm
	 }
	 with(document.all.hw_2)
	 {
	  var hwn = 0
	  hwSelect_s13 = (hwwhere[hwm].hwSelect_s2).split(TheSplit3);
	  length = hwSelect_s13.length;
	  for(hwl=0;hwl<length;hwl++)
	  {
	   if (hwSelect_s13[hwl].indexOf(TheSplit4)!=-1)
	   {
	    options[hwl].text = hwSelect_s13[hwl].split(TheSplit4)[0];
	    options[hwl].value = hwSelect_s13[hwl].split(TheSplit4)[1];
	    if (hwdefault_value.indexOf(hwSelect_s13[hwl].split(TheSplit4)[1])!=-1){hwn = hwl}
	   }
	   else
	   {
	    options[hwl].text = hwSelect_s13[hwl];
	    options[hwl].value = hwSelect_s13[hwl];
	    if (hwdefault_value.indexOf(hwSelect_s13[hwl])!=-1){hwn = hwl}
	   }
	  }
	  selectedIndex = hwn
	 }
	 
	}
	//hw_init();
	
	
	
	

	function ajaxRequest(){
		var name = $("#name").val();
		var p1 = $("#password").val();
		var p2 = $("#passworda").val();
		var tele = $("#tele").val();
        $.ajax({
          url: "ajax",
          type: "POST",
          dataType: "json",
          data: {
            "name":name,
            "p1": p1,
            "p2": p2,
          	"tele":tele
          },
          async: true,
          success: function(data) {
        	  $('#registerBtn').attr("disabled",false);
            if(data.name=="1"){
            	$("#checkName").show();
            	$("#checkName2").hide();
            	$("#checkName3").hide();
            	$('#registerBtn').attr("disabled",true);
            }else if(data.name=="0"){
            	$("#checkName2").show();
            	$("#checkName").hide();
            	$("#checkName3").hide();
            	$('#registerBtn').attr("disabled",true);
            }else if(data.name=="2"){
		        $("#checkName3").show();
		        $("#checkName2").hide();
		    	$("#checkName").hide();
		        $('#registerBtn').attr("disabled",true);
            }else{$("#checkName").hide();$("#checkName2").hide();$("#checkName3").hide();}
            
            
            if(data.tele=="1"){
            	$("#checkTele").show();
            	$("#checkTele2").hide();
            	$('#registerBtn').attr("disabled",true);
            }else if(data.tele=="0"){
            	$("#checkTele2").show();
            	$("#checkTele").hide();
            	$('#registerBtn').attr("disabled",true);
            }else{$("#checkTele").hide();$("#checkTele2").hide();}
            
            if(data.pass=="1"){
            	$("#checkPass").show();
            	$("#checkPass2").hide();
            	$("#checkPass3").hide();
            	$('#registerBtn').attr("disabled",true);
            }else if(data.pass=="0"){
            	$("#checkPass2").show();
            	$("#checkPass").hide();
            	$("#checkPass3").hide();
            	$('#registerBtn').attr("disabled",true);
            }else if(data.pass=="2"){
	            $("#checkPass3").show();
	            $("#checkPass").hide();
	            $("#checkPass2").hide();
	            $('#registerBtn').attr("disabled",true);
            }else{$("#checkPass").hide();$("#checkPass2").hide();$("#checkPass3").hide();}
            
          },
          error: function() {
            alert("error");
          }
        });
      }