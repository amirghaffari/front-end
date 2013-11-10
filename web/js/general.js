/*Set Global Values*/
var SlideNO = 1;
var hide_show_time=500;//one second

$(document).ready(function(){
    setup();
});

function setup() {
    //slideshow();
    initTasks();
    retrieveScrollPositionFromCookie();    
    initPopup();
    initTooltip();
    initScrollPosition();
    //initConfig();
}

function slideshow() {
    var TotalNumber = 1;
    var intervalOfChanges = 5000;
    var AddressOfSlides = "style/images/slide";
    if (TotalNumber == 1) return;
    var s = AddressOfSlides + SlideNO + ".jpg";
    id = document.getElementById("slidepanel");
    id.setAttribute("src", s);
    id.setAttribute("alt", "");
    SlideNO = (SlideNO) % TotalNumber;
    SlideNO = SlideNO + 1;
    setTimeout("slideshow()", intervalOfChanges);
    
}

function setCookie(c_name,value,days,hours,minutes)
{
    var exdate=new Date();
    exdate.setDate(exdate.getDate() + days);
    exdate.setMinutes(exdate.getMinutes() + minutes);
    exdate.setHours(exdate.getHours() + hours);
    var c_value=escape(value) + (((days==null) && (days==null) && (hours==null) && (minutes=null)) ? "" : "; expires="+exdate.toUTCString());
    document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
var i,x,y,ARRcookies=document.cookie.split(";");
for (i=0;i<ARRcookies.length;i++)
{
  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
  x=x.replace(/^\s+|\s+$/g,"");
  if (x==c_name)
    {
        return unescape(y);
    }
  }
}

function setup_google() {
    google.load('search', '1', {language : 'en', style : google.loader.themes.MINIMALIST});
    google.setOnLoadCallback(function() {
        var customSearchControl = new google.search.CustomSearchControl('013164300918868845690:sl3xhk5zqpi');
        customSearchControl.setResultSetSize(google.search.Search.SMALL_RESULTSET);
        customSearchControl.draw('cse');
        }, true);
}

function Hide( id )
{
    $('#'+id+'_1').show(hide_show_time);
    $('#'+id+'_2').hide(hide_show_time);
    $('#'+id+'_3').hide(hide_show_time);
    return false;
}

function Appear( id )
{
    $('#'+id+'_1').hide(hide_show_time);
    $('#'+id+'_2').show(hide_show_time);
    $('#'+id+'_3').show(hide_show_time);
    return false;
}


function setSelectedAction(id)
{
    var value = $('#'+id).attr("value");
    $("#selected_action").attr("value",value);
}

function setSelectedActionByConfirm(id,title,message,accept,deny,form)
{
    var value = $('#'+id).attr("value");
    $("#selected_action").attr("value",value);
    cancelConfirmm(id,title,message,accept,deny,form);
    return false;
}

function cancelConfirmm(id,title,message,accept,deny,form)
{
    		resultReturn= $.confirm({
			'title'		: title, //'Delete Confirmation',
			'message'	: message, //'You are about to delete this item. <br />It cannot be restored at a later time! Continue?',
			'buttons'	: {
			accept/*'Yes'*/	: {
					'class'	: 'blue',
                                        'caption': accept,
					'action': function(){
                                                $("#"+form).submit();

					}
			},
			deny/*'No'*/: {
					'class'	: 'gray',
                                        'caption': deny,
					'action': function(){}	
				}
			}
		});
}

function confirmdelete(id,title,message,accept,deny)
{
                saveScrollPositionToCookie();
    		resultReturn= $.confirm({
			'title'		: title, //'Delete Confirmation',
			'message'	: message, //'You are about to delete this item. <br />It cannot be restored at a later time! Continue?',
			'buttons'	: {
			accept/*'Yes'*/	: {
					'class'	: 'blue',
                                        'caption': accept,
					'action': function(){
                                                var value = $('#td_'+id).attr("rev");
                                                $.ajax({url:value, success:function(){
                                                    $('#tr_'+id).fadeOut("slow");
                                                    retrieveScrollPositionFromCookie();
                                                }
                                            });
					}
			},
			deny/*'No'*/: {
					'class'	: 'gray',
                                        'caption': deny,
					'action': function(){retrieveScrollPositionFromCookie();}	
				}
			}
		});
}

function initScrollPosition()
{
    $('.keepScrollPosition').keepScroll();
    $('.clearScrollPosition').clearScroll();
}

$.fn.clearScroll = function(){
    var $this = $(this);
    $this.click(function() { 
        resetScrollPositionInCookie();
    }
    );
};

$.fn.keepScroll = function(){
    var $this = $(this);
    $this.click(function() { 
        saveScrollPositionToCookie();
    }
    );
};

function resetScrollPositionInCookie()
{
    alert('reset scroll position');
    var scrollTop=0;
    var scrollLeft=0;
    setCookie("keepScrollPositionTop",scrollTop,0,0,1);
    setCookie("keepScrollPositionLeft",scrollLeft,0,0,1);
}

function saveScrollPositionToCookie()
{
    var scrollTop;
    var scrollLeft;
    scrollTop=$('body').scrollTop();
    scrollLeft=$('body').scrollLeft();
    setCookie("keepScrollPositionTop",scrollTop,0,0,1);
    setCookie("keepScrollPositionLeft",scrollLeft,0,0,1);
}

function retrieveScrollPositionFromCookie()
{
    var scrollTop;
    var scrollLeft;
    scrollTop=getCookie("keepScrollPositionTop");
    scrollLeft=getCookie("keepScrollPositionLeft");
    if(scrollLeft)
        $('body').scrollLeft(scrollLeft);
    if(scrollTop)
        $('body').scrollTop(scrollTop);
}

function initTasks(){

    $('.numeric').ForceNumericOnly();
    
    $('#commentTextArea').focus(function() {
        $('#noteForComment').show(hide_show_time*2);
    });

    $('#commentTextArea').blur(function() {
        $('#noteForComment').hide(hide_show_time*2);
    });
    
    var averag = $('#publication_abstract_rating_average').attr("class");
    for (i=1;i<=averag;i++)
    {
        $('#publication_abstract_rating_average'+i).removeClass("rating_star_empty");
        $('#publication_abstract_rating_average'+i).addClass("rating_star_over");
    }

    $('.rating_group').hover(
        // Handles the mouseover
        function() {
            var id = $(this).attr("id");
            for (i=1;i<=5;i++)
            {
                if($('#'+id+'_'+i).hasClass('rating_star_full')){
                    $('#'+id+'_'+i).removeClass("rating_star_full");
                    $('#'+id+'_'+i).addClass("rating_star_over");
                }
            }
        },
        // Handles the mouseout
        function() {
            var id = $(this).attr("id");
            for (i=1;i<=5;i++)
            {
                if($('#'+id+'_'+i).hasClass('rating_star_over')){
                    $('#'+id+'_'+i).removeClass("rating_star_over");
                    $('#'+id+'_'+i).addClass("rating_star_full");
                }
            }
        }
    );
    $('.rating_group').click(
        function() {
            var id = $(this).attr("id");
            $('#'+id+'_choose').attr('checked', true);
        }
    );

    $(".newsticker-jcarousellite").jCarouselLite({
                    vertical: true,
                    hoverPause:true,
                    visible: 5,
                    auto:500,
                    speed:1000
            });

    $("select.pageGroupSelectTag").change(function () {
          var str = "";
          $("select.pageGroupSelectTag option:selected").each(function () {
                //str += $(this).text() + " " + $(this).attr("value");
                str=$(this).attr("value");
                if(str=="-1") {
                    showPopupWindows($(this).attr("class"),400,'addpapergroup');
                }
              });
              //
        })
        .trigger('change');

    $("select").change(function () {
          var str = "";
          $("select#template option:selected").each(function () {
                //str += $(this).text() + " " + $(this).attr("value");
                str=$(this).attr("title");
                $("#review").val(str);
              });
              //
        })
        .trigger('change');
}

// return the value of the radio button that is checked
// return false if none are checked, or
// there are no radio buttons
function checkRadioButtons() {

    allElements = $('.ratingRadio');
    var element;
    for (var i = 0; allElements[i] != null; i++) {
        element = allElements[i]
        if(element.checked) {$('#radio_button_error').hide(hide_show_time); return true;}
    }
    $('#radio_button_error').show(hide_show_time);
    return false;
}

// Numeric only control handler
jQuery.fn.ForceNumericOnly =
function()
{
    return this.each(function()
    {
        $(this).keydown(function(e)
        {
            var key = e.charCode || e.keyCode || 0;
            // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
            return (
                key == 8 || 
                key == 9 ||
                key == 46 ||
                (key >= 37 && key <= 40) ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105));
        });
    });
};

function checkSelectIsSelected(id,message){
    var foo = document.getElementById(id);
    if (foo)
    {
       if (foo.selectedIndex == null || foo.selectedIndex==-1)
       {
           alert(message);
           return false;
       } 
    }
    return true;
        
}

function textBoxNotNull(id)
{
    var foo = document.getElementById(id);
    if (foo)
    {
        if(foo.value=="") {alert("title is required");return false;}
    }
    return true;
}