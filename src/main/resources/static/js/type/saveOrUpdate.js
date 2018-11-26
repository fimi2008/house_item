/**
 * 新增联系人

 * created on 2018/5/14 14:59
 *
 * @author lionxxw
 * @version 1.0.0
 */
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var WxType = {
    modelId: 'md_contacts_model',
    url: '/crm/contacts/save',
    selectEnums: '/crm/ajax/selectEnums',
    $model: Object,
    $entityCode: Object,
    $entityName: Object,
    $productCode: Object,
    $productName: Object,
    $form: Object,
    $selectCustomerDiv: Object,
    $clearBtn: Object,
    $subject: Object,
    $typeCode: Object,
    $selectEntity: Object,
    $submitBtn: Object,
    $birthday: Object,
    $selectProductBtn: Object,
    typeCodes: [],
    contactsRoles:[],
    $contactsRole:Object,
    subjects: [],
    files:[],
    initEnums: function () {
        this.selectSubjects();
        this.selectTypes();
        this.selectContactsRoles();
    },
    isEmpty:function(value){
        return (Array.isArray(value) && value.length === 0) || (Object.prototype.isPrototypeOf(value) && Object.keys(value).length === 0);
    },
    selectSubjects: function () {
        $.get(MdAddContacts.selectEnums, {key: 'CRM_SUBJECT'}, function (result) {
            if (result.code === 200) {
                MdAddContacts.subjects = result.data;
            } else {
                console.log(result.message);
            }
        }, 'json');
    },
    selectTypes: function () {
        $.get(MdAddContacts.selectEnums, {key: 'CRM_CONTACTS_TYPE'}, function (result) {
            if (result.code === 200) {
                MdAddContacts.typeCodes = result.data;
            } else {
                console.log(result.message);
            }
        }, 'json');
    },

    selectContactsRoles: function () {
        $.get(MdAddContacts.selectEnums, {key: 'CONTACTS_ROLE'}, function (result) {
            if (result.code === 200) {
                MdAddContacts.contactsRoles = result.data;
            } else {
                console.log(result.message);
            }
        }, 'json');
    },


    initJqueryDom: function () {
        this.$model = $('#' + this.modelId);
        this.$entityCode = $('#' + this.modelId + '_entityCode');
        this.$entityName = $('#' + this.modelId + '_entityName');
        this.$productCode = $('#' + this.modelId + '_productCode');
        this.$productName = $('#' + this.modelId + '_productName');
        this.$form = $('#' + this.modelId + '_form');
        this.$selectCustomerDiv = $('#' + this.modelId + '_select_customer_div');
        this.$clearBtn = $('#' + this.modelId + '_clear_btn');
        this.$subject = $('#' + this.modelId + '_subject');
        this.$typeCode = $('#' + this.modelId + '_typeCode');
        this.$selectEntity = $('#' + this.modelId + '_select_entity');
        this.$submitBtn = $('#' + this.modelId + '_submit_btn');
        this.$birthday = $('#' + this.modelId + '_birthday');
        this.$selectProductBtn = $('#' + this.modelId + '_select_product_btn');
        this.$contactsRole=$('#' + this.modelId + '_contactsRole');
    },
    // 初始化主体
    initSubject: function (subject) {
        if (null == MdAddContacts.subjects || MdAddContacts.subjects.length === 0) {
            this.selectSubjects();
        } else {
            var html = '<option value="">请选择业务线</option>';
            $.each(MdAddContacts.subjects, function (index, item) {
                if (null != subject && item.enumCode === subject) {
                    html += '<option selected="selected" value ="' + item.enumCode + '">' + item.enumName + '</option>';
                    MdSelectProduct.changeSubject(subject);
                } else {
                    html += '<option value ="' + item.enumCode + '">' + item.enumName + '</option>';
                }
            });
            this.$subject.html(html);
        }
    },
    // 初始化所属类别
    initType: function (typeCode) {
        if (null == MdAddContacts.typeCodes || MdAddContacts.typeCodes.length === 0) {
            this.selectTypes();
        } else {
            var html = "<option value=''>请选择类别</option>";
            $.each(MdAddContacts.typeCodes, function (index, item) {
                if (null != typeCode && item.enumCode === typeCode) {
                    html += '<option selected="selected" value ="' + item.enumCode + '">' + item.enumName + '</option>';
                } else {
                    html += '<option value ="' + item.enumCode + '">' + item.enumName + '</option>';
                }
            });
            this.$typeCode.html(html);
        }
    },
    // 初始化日历控件
    initDatetime: function () {
        this.$birthday.datetimepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            startView: 2,
            minView: 2,
            language: 'zh-CN',
            todayBtn: true,
            clearBtn: true
        }).on('hide', function (event) {
            event.preventDefault();
            event.stopPropagation();
        });
    },
    initProvince:function () {
        $('.city').distpicker();
    },
    // 绑定选择机构
    bindSelectEntity: function () {
        this.$selectEntity.on('click', function () {
            var val = MdAddContacts.$typeCode.val();
            if ('' === val) {
                Ewin.alert('请先选择所属类别');
                return;
            }
            if ('SUPPLIER' === val) {
                console.log('SUPPLIER');
            } else if ('CUSTOMER' === val) {
                console.log('CUSTOMER');
            }
        });
    },
    // 绑定主体切换
    bindChangeSubject: function () {
        this.$subject.on('change', function () {
            var val = $(this).val();
            MdSelectProduct.changeSubject(val);
        });
    },
    // 绑定选择产品
    bindSelectProduct: function () {
        this.$selectProductBtn.on('click', function () {
            MdSelectProduct.show('radio', '', '', function (productCode, productName) {
                MdAddContacts.$productCode.val(productCode);
                MdAddContacts.$productName.val(productName);
                MdAddContacts.$clearBtn.show();
            });
        });
    },
    // 创建model
    appendModel: function (tpl, subject, typeCode, entityCode, entityName, callBack) {
        $('body').append(tpl);
        this.initJqueryDom();
        this.formValidator();
        var fileType = ['png', 'jpg', 'jpeg', 'bmp', 'gif', 'webp', 'psd', 'svg', 'tiff'];
        //文件输入框的操作
        $(".files-upload").on("change","#file-input-form",function(e) {
            var file = e.target.files; //获取图片资源
            var index= file[0].name.lastIndexOf(".");
            var subprix = file[0].name.substr(index + 1);
            if(!(fileType.indexOf(subprix) !== -1)){
                Ewin.alert("文件格式不正确，请检查文件格式！");
                return ;
            }
            MdAddContacts.files[0] = file;
            var img ="";
            var reader = new FileReader();
            reader.readAsDataURL(file[0]); // 读取文件
            // 渲染文件
            reader.onload = function (args) {
                img += "<li><div class='pre-con'><img class='preview' src='" + args.currentTarget.result + "'alt='preview'/><span>" + file[0].name + "</span></div>" +
                    "<div class='pre-mask'></div><i class='del-icon' title='删除'></i></li>";
                $(".preview-list").html(img);
            }
            MdAddContacts.$form.data('bootstrapValidator').updateStatus('name','NOT_VALIDATED',null).validateField('name');
        });
        //删除文件
        $('.preview-list').on('click','.del-icon',function(e){
            MdAddContacts.files.splice(0, MdAddContacts.files.length);
            e.currentTarget.parentNode.remove();
        });

        this.$model.on('hide.bs.modal', function () {
            MdAddContacts.$model.remove();
        });
        $(document).keydown(function (event) {
            if (event.keyCode === 13) {
                MdAddContacts.$submitBtn.click();
            }
        });
        this.initDatetime();
        this.initSubject(subject);
        this.initType(typeCode);
        this.initProvince();
        this.bindSelectEntity();
        this.bindClearBtn();
        this.bindAddBtn(callBack);
        this.bindChangeSubject();
        this.bindSelectProduct();
        this.changeEntity(entityCode, entityName);
        this.$model.modal('show');
    },
    changeEntity: function (entityCode, entityName) {
        this.$entityCode.val(entityCode);
        this.$entityName.val(entityName);
    },
    add: function (subjects, types, callBack) {
        var tpl = this.tpl.contactsForm('新建联系人');
        this.appendModel(tpl, null, null, '', '', callBack);
    },
    /**
     * 快速添加客户联系
     * @param subject
     * @param entityCode
     * @param entityName
     */
    fastAdd_Cus: function (subject, customerTypeCode,customerCode, customerName, callBack) {
        var tpl = this.tpl.contactsForm('新建联系人');
        this.appendModel(tpl, subject, 'CUSTOMER', customerCode, customerName, callBack);
        this.$subject.attr("disabled", "disabled");
        this.$typeCode.attr("disabled", "disabled");
        $('#rl_contactsRole').multiselect({
            nonSelectedText: '请选择角色',
            nSelectedText: '选中个数',
            allSelectedText: '全部选中'
        });
        if("Group"==customerTypeCode){
            $("#rl_typeNameFlag").val("1");
            $('#rl_contactsRole').val("");
            $("#rl_contactsRoleDiv").show();
        }else{
            $("#rl_contactsRoleDiv").hide();
        }

    },
    formValidator: function () {
        this.$form.bootstrapValidator({
            message: '这个属性不能为空',
            fields: {
                name: {
                    message: '请填写内容',
                    validators: {
                        notEmpty: {
                            message: '请填写联系人名称'
                        },
                        stringLength: {
                            max: 16,
                            message: '联系人名称长度请不要超过16个字符'
                        }
                    }
                },
                subjectCode: {
                    message: '请选择业务线',
                    validators: {
                        notEmpty: {
                            message: '请选择业务线'
                        }
                    }
                },
                typeCode: {
                    message: '请选择客户类型',
                    validators: {
                        notEmpty: {
                            message: '请选择客户类型'
                        }
                    }
                },
                entityName: {
                    validators: {
                        notEmpty: {
                            message: '请选择机构名称'
                        }
                    }
                },
                email: {
                    validators: {
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                },
                telephone: {
                    validators: {
                        regexp: {
                            regexp: /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/,
                            message: '电话号码输入格式有误'
                        }
                    }
                }

            }
        });
    },
    disabled: function () {
        $('#contactsType').attr("disabled", "disabled");
        this.$entityName.next().remove();
    },
    // 绑定提交按钮
    bindAddBtn: function (fc) {
        this.$submitBtn.on('click', function () {
            MdAddContacts.$subject.removeAttr("disabled");
            MdAddContacts.$typeCode.removeAttr("disabled");
            var bootstrapValidator = MdAddContacts.$form.data('bootstrapValidator');

            var typeNameFlag =  $("#rl_typeNameFlag").val();

            if (typeNameFlag == "1") {//类型为集团的验证

                MdAddContacts.$form.bootstrapValidator('removeField', 'department');
                MdAddContacts.$form.bootstrapValidator('removeField', 'duties');
                MdAddContacts.$form.bootstrapValidator('removeField', 'mobile');
                MdAddContacts.$form.bootstrapValidator("addField", "mobile", {
                    validators: {
                        regexp: {
                            regexp: /^1[3|7|9|8|5|4|][0-9]\d{4,8}$/,
                            message: '手机号输入格式有误'
                        }
                    }
                });
                MdAddContacts.$form.bootstrapValidator("addField", "contactsRoleList", {
                    validators: {
                        notEmpty: {
                            message: '请选择角色'
                        },
                    }
                });


            } else {

                MdAddContacts.$form.bootstrapValidator("addField", "department", {
                    validators: {
                        notEmpty: {
                            message: '部门不能为空'
                        },
                    }
                });

                MdAddContacts.$form.bootstrapValidator("addField", "duties", {
                    validators: {
                        notEmpty: {
                            message: '职务不能为空'
                        },
                    }
                });

                MdAddContacts.$form.bootstrapValidator("addField", "mobile", {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^1[3|7|9|8|5|4|][0-9]\d{4,8}$/,
                            message: '手机号输入格式有误'
                        }
                    }
                });
            }





            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                if (typeNameFlag != "1") {//类型为集团的验证
                    if (MdAddContacts.isEmpty(MdAddContacts.files)) {
                        Ewin.alert("请上传联系人名片！");
                        return;
                    }
                }
                $up.upload({
                    url: MdAddContacts.url,
                    files: MdAddContacts.files,
                    data: {crmContacts: JSON.stringify(MdAddContacts.$form.serializeObject())},
                    success: function (data) {
                        if (data.code == 200) {
                            if (typeof(fc) === 'function') {
                                fc(data.data);
                            }
                            MdAddContacts.$model.modal('hide');
                        }
                        else {
                            Ewin.alert("操作失败！");
                        }
                    }
                });
                // $.post(MdAddContacts.url, MdAddContacts.$form.serialize(), function (result) {
                //     if (result.code === 200) {
                //         if (typeof(fc) === 'function') {
                //             fc(result.data);
                //         }
                //         MdAddContacts.$model.modal('hide');
                //     } else {
                //         Ewin.alert(result.message);
                //     }
                // }, 'json');
            }
        });
    },
    // 绑定产品清除按钮
    bindClearBtn: function () {
        this.$productName.val('');
        this.$productCode.val('');
        this.$clearBtn.hide();
    },
    tpl: {
        nullString: function (obj) {
            if (obj === null) return '';
            return obj;
        },
        contactsForm: function (title) {
            var html="";
            html=html+'<div class="modal fade edit-merchant overflow" data-backdrop="static" data-keyboard="false" id="' + MdAddContacts.modelId + '" bs-example-modal-sm tabindex="-1" role="dialog"' +
                '         aria-labelledby="myModalLabel">' +
                '        <div class="modal-dialog" role="document">' +
                '            <div class="modal-content">' +
                '                <div class="modal-header">' +
                '                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span' +
                '                            aria-hidden="true">&times;</span></button>' +
                '                    <h4 class="modal-title">' + title + '</h4>' +
                '                </div>' +
                '                <div class="modal-body overflow">' +
                '                    <form class="form-horizontal"id="' + MdAddContacts.modelId + '_form" action="" method="post" onsubmit="return false;">' +
                '                        <input type="hidden" name="id">' +
                '                        <div class="form-group">' +
                '                            <label class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>姓名</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" maxlength="16" name="name" placeholder="请输入联系人姓名">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>业务线</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <select name="subjectCode" class="form-control" id="' + MdAddContacts.modelId + '_subject">' +
                '                                </select>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label class="col-sm-2 col-md-3 control-label">产品</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="hidden" id="' + MdAddContacts.modelId + '_productCode" name="productCode">' +
                '                                <input type="text" class="form-control" name="productName" id="' + MdAddContacts.modelId + '_productName" readonly="readonly" placeholder="请选择相应的产品">' +
                '                                <span class="form-control-feedback" id="' + MdAddContacts.modelId + '_select_product_btn">＋</span>' +
                '                                <a href="javascript:;" id="' + MdAddContacts.modelId + '_clear_btn"' +
                '                                   style="position: absolute; top: 10px;right: -12px;color:#3184ef;display: none;">清除</a>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>所属类别</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <select class="form-control" id="' + MdAddContacts.modelId + '_typeCode" name="typeCode">' +
                '                                    <option value="">请选择类别</option>' +
                '                                </select>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>机构名称</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="hidden" id="' + MdAddContacts.modelId + '_entityCode" name="entityCode">' +
                '                                <input type="text" class="form-control" name="entityName" id="' + MdAddContacts.modelId + '_entityName" readonly="readonly" placeholder="请选择机构名称">' +
                '                                <span class="form-control-feedback input-company" id="' + MdAddContacts.modelId + '_select_entity" >＋</span>' +
                '                            </div>' +
                '                        </div>' +
                '<div class="form-group  has-feedback" id="rl_contactsRoleDiv">'+
                '<input type="hidden" name="typeNameFlag" id="rl_typeNameFlag">'+
                '<label  class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>角色</label>'+
                '<div class="col-sm-10 col-md-6 col-sm-8">'+
                '<select class="form-control"  id="rl_contactsRole" name="contactsRoleList" multiple="multiple">';

            var roles=MdAddContacts.contactsRoles;
            for(var i=0;i<roles.length;i++){
                html=html+'<option  value='+roles[i].enumCode+'>'+roles[i].enumName+'</option>';
            }

            html=html+'</select>'+
                '</div>'+
                '</div>'+
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>部门</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" maxlength="32" name="department" placeholder="请输入联系人所属部门">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>职务</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" maxlength="32" name="duties" placeholder="请输入联系人职务">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>手机</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" name="mobile" placeholder="请输入联系人手机号">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label">电话</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" name="telephone" placeholder="请输入联系人座机号">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label class="col-sm-2 col-md-3 control-label">电子邮件</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" name="email" placeholder="请输入联系人电子邮件">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label class="col-sm-2 col-md-3 control-label">微博</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" name="blog" placeholder="请输入联系人微博名称">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label">性别</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <select class="form-control" name="gender">' +
                '                                    <option value="M">男</option>' +
                '                                    <option value="F">女</option>' +
                '                                </select>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label">出生日期</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" name="birthDayTime" readonly id="' + MdAddContacts.modelId + '_birthday" placeholder="请输入联系人出生日期">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="city" data-toggle="distpicker">' +
                '                            <div class="form-group  ">' +
                '                                <label class="col-sm-2 col-md-3 control-label">省份</label>' +
                '                                <div class="col-sm-10 col-md-6">' +
                '                                    <select class="form-control" name="province"></select>' +
                '                                </div>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label">地址</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" maxlength="64" name="address" placeholder="请输入联系人办公地址">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group  has-feedback">' +
                '                            <label  class="col-sm-2 col-md-3 control-label">邮编</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="text" class="form-control" maxlength="8" name="zipcode" placeholder="请输入联系人办公地址邮编">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group">' +
                '                            <label for="l3" class="col-sm-2 col-md-3 control-label">备注</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <textarea class="form-control" name="remarks" maxlength="256" placeholder="请输入备注"></textarea>' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group">' +
                '                            <label  class="col-sm-2 col-md-3 control-label"><i' +
                '                                    class="need-icon">*&nbsp;</i>所属部门</label>' +
                '                            <div class="col-sm-10 col-md-6">' +
                '                                <input type="hidden" class="form-control" name="belongDecode" value="ALL">' +
                '                                <input type="text" readonly class="form-control" name="belongDename" value="全公司" placeholder="请输入所属部门">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="form-group link files-upload">' +
                '                           <label  class="col-sm-2 col-md-3 control-label"><i class="need-icon">*</i>名片</label> ' +
                '                           <div class="col-sm-10 col-md-6">' +
                '                               <div class="upload-btn">上传 ' +
                '                                   <input id="file-input-form" type="file" name="files"> ' +
                '                               </div> ' +
                '                               <ul class="preview-list"> ' +
                '                               </ul> ' +
                '                           </div> ' +
                '                        </div>'+
                '                        <div class="modal-footer">' +
                '                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
                '                            <button type="submit" class="btn btn-primary" id="' + MdAddContacts.modelId + '_submit_btn">保存</button>' +
                '                        </div>' +
                '                    </form>' +
                '                </div>' +
                '            </div>' +
                '        </div>' +
                '    </div>';
            return $(html);
        }
    }
};