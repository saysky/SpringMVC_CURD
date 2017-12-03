/**
 * 添加用户表单验证
 * admin/user/add.jsp
 */
$(document).ready(function() {
    $('#addUserForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '用户名应该为4-30个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '只能输入数字、字母和下划线'
                    },
                    callback: {
                        message: '用户名已存在',//false执行
                        callback: function(value, validator) {
                            var flag = true;
                            $.ajax({
                                async: false,
                                type: "POST",
                                url: $("#PageContext").val()+'/admin/user/isUsernameExisted',
                                data: {
                                    "username": $("#username").val(),
                                    "id":$("#id").val()
                                },
                                dataType: "json",
                                success: function (data) {
                                    if(data.code == 1) {
                                       // $.alert('用户名已存在', '警告');
                                        flag = false;
                                    }
                                },
                                error: function () {}
                            })
                            return flag;
                        }
                    }

                }
            },
            nickname: {
                message: '昵称不合法',
                validators: {
                    notEmpty: {
                        message: '昵称不可为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '昵称应该为2-20个字符'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5a-zA-Z0-9_\.]+$/,
                        message: '只能输入中文、数字、字母和下划线'
                    }
                }
            },
            email: {
                validators: {
                    emailAddress: {
                        message: '这可能不是一个有效的Email地址'
                    }
                }
            },
            website: {
                validators: {
                    uri: {
                        allowLocal: true,
                        message: '这可能不是一个有效的URL'
                    }
                }
            },
            phone: {
                message: 'The phone is not valid',
                validators: {
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    },
                    regexp: {
                        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                        message: '这可能不是一个有效的手机号码'
                    }
                }
            },
            avatar: {
                validators: {
                    file: ""
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不可为空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '密码需要和有效密码一致'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不可为空'
                    },
                    identical: {
                        field: 'password',
                        message: '确认密码需要和密码一致'
                    }
                }
            }
        },
    });
});

/**
 * 全选和取消全选
 * @constructor
 */
function DoCheck() {
    var ch = document.getElementsByName("ids");
    if (document.getElementById("allSelect").checked == true) {
        for (var i = 0; i < ch.length; i++) {
            ch[i].checked = true;
        }
    } else {
        for (var i = 0; i < ch.length; i++) {
            ch[i].checked = false;
        }
    }
}


//ajax上传图片（用户头像）
function uploadImg() {
    if($("#file").val() != "") {
        $.ajaxFileUpload({
            type: "POST",
            url:$("#PageContext").val()+"/uploadFile",
            dataType: "json",
            fileElementId:"file",
            success: function(d){
                if(d.code == 0) {
                    //alert("上传成功");
                    //图片显示
                    $("#avatar").attr("value",d.data.url);
                    $("#avatarShow").attr("src",d.data.url);
                }
            },
            error: function () {
                alert("上传失败");
            },
        });
    } else {
        alert("请先选择文件");
    }
}
/**
 * 单个删除用户
 * @returns {boolean}
 */alert

function deleteUser(id) {
    $.confirm({
        title: '温馨提示',
        content: '你确认要删除吗？',
        buttons: {
            取消: function () {},
            确认: function () {
                $.ajax({
                    async: false,
                    type: "POST",
                    url: $("#PageContext").val()+'/admin/user/delete',
                    data: {"id": id},
                    dataType: "json",
                    complete: function () {
                        $("#content").load(window.location.href+" .table-responsive");
                        //window.location.reload();
                    }
                })

            }
        },
    });
}


/**
 * 批量删除确认删除
 * @returns {boolean}
 */
function deleteUserMore() {
   $.confirm({
        title: '温馨提示',
        content: '你确认要删除吗？',
        buttons: {
            取消: function () {},
            确认: function () {
                var str = $("input:checkbox[name='ids']:checked").map(function(index,elem) {
                    return $(elem).val();
                }).get().join(',');
                if(str != "") {
                $.ajax({
                    async: false,
                    type: "POST",
                    url: $("#PageContext").val()+'/admin/user/deleteMore',
                    data: {"ids": str},
                    dataType: "json",
                    success: function () {
                        $.alert('删除成功', '温馨提示');
                    },
                    error: function () {
                        $.alert('删除失败', '温馨提示');
                    },
                    complete: function () {
                        $("#content").load(window.location.href+" .table-responsive");
                        //window.location.reload();
                    }
                })
                }else {
                    $.alert('请至少勾选一项', '温馨提示');
                }
            }
        },
    });
}

/**
 * ajax 验证用户名是否存在
 */
function checkUsername() {
    $.ajax({
        async: false,
        type: "POST",
        url: $("#PageContext").val()+'/admin/user/isUsernameExisted',
        data: {"username": $("#username").val()},
        dataType: "json",
        success: function (data) {
            if(data.code == 1) {
                //$.alert('用户名已存在', '警告');
                return false;
            }
        },
        error: function () {}
    })
    return false;
}
