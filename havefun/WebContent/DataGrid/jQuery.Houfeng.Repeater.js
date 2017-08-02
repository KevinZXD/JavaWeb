/*****************************************************************
* 文　件: jQuery.Houfeng.Repeater
* 说　明: javascript版的Repeater
* ****************************************************************
* 创建者: Houfeng
* Email : admin@xhou.net
*****************************************************************/

jQuery.fn.extend({
    Repeater:function(val,ItemCreatedCallBack){
          this.each(function(){
              if(val==null || val==undefined){
                        return $(this).data("_DataSrc");
              }
              else{
                      //
                      try{
                      var valtype=(typeof val).toString();
                      if(valtype=='string')
                            val =jsonStringToDataTable(val).rows;
                       }catch(ex){
                            return ;
                       }
                      //
                      var domtype=$(this).find(".itemtemplate").attr('nodeName');
                      //
                      if($(this).data("_ItemTemplate")==null ){
                          $(this).data("_ItemTemplate",$(this).find(".itemtemplate").toHTML());
                          $(this).find(".itemtemplate").remove();
                      } 
                      var TrContentTemplate=$(this).data("_ItemTemplate");
                      //
                      var fileds=____FindFiled(TrContentTemplate);
                      if(fileds==null )return false ;
                      var filedscount=fileds.length;
                      ////
                      $(this).data("_DataSrc",val); 
                      var count=val.length;
                      for(var i=0;i<count ;i++){
                        
                         var NewTrContent=TrContentTemplate;
                         //
                         NewTrContent=NewTrContent.Replace("{{","{#");
                         NewTrContent=NewTrContent.Replace("}}","#}");
                         for( var j=0;j<filedscount;j++){
                               NewTrContent=NewTrContent.Replace("{"+fileds[j]+"}",val[i][fileds[j].trim()]);
                         }
                         NewTrContent=NewTrContent.Replace("{#","{");
                         NewTrContent=NewTrContent.Replace("#}","}");
                         //
                         var area=$(this).find('tbody');
                         if(area ==null )
                                area =$(this);
                         //
                         area.append(NewTrContent);
                         if(ItemCreatedCallBack!=null ){
                            ItemCreatedCallBack($(this).find(domtype+":last"));
                         }
                      }
                      //
                      $(this).RepeaterSetItemClass($(this).data("_class1"),$(this).data("_class2"),$(this).data("_hoverClass"));
              }
          });
    },
    RepeaterClear:function (){
        this.each(function(){
              if($(this).data("_ItemTemplate")==null ){
                  $(this).data("_ItemTemplate",$(this).find(".itemtemplate").toHTML());
              } 
              $(this).find(".itemtemplate").remove();
        });
    },
    RepeaterSetItemClass:function (class1,class2,hoverClass){//行样式
        this.each(function(){
            if(class1==null || class2==null || hoverClass ==null )
                return ;
                
               $(this).data("_class1",class1);
               $(this).data("_class2",class2);
               $(this).data("_hoverClass",hoverClass);
               //
               if($(this).data("_DataSrc")!=null ){
                    var domtype=$(this).find(".itemtemplate").attr('nodeName');
                   //
                   $(this).find(domtype).addClass(class1);
                   $(this).find(domtype+":nth-child(even)").addClass(class2);
                  // $(this).find(domtype+":first").removeClass(class1);
                   
                   $(this).find(domtype).hover(function(){$(this).addClass(hoverClass);},function(){$(this).removeClass(hoverClass);}); 
           }
       });
    }   
});


function ____FindFiled(str){
    var myRegex = new RegExp("\{.+?\}", "gim");
    //var arr = myRegex.exec(str);
    var arr=str.match(myRegex);
    if(arr ==null )return null ;
    var count=arr.length;
    for( var i=0;i<count;i++)
    {
       arr[i]=arr[i].Replace("{","").Replace("}","");
    }
    return arr ;
}

//----------------------------------------------------------------------
