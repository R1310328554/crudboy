package cn.iocoder.yudao.module.infra.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * infra 系统，使用 1-001-000-000 段
 */
public interface ErrorCodeConstants {

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 网站内容爬取训练 TODO 补充编号 ==========
ErrorCode WEBSITE_TRAINING_NOT_EXISTS = new ErrorCode(1000001, "网站内容爬取训练不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 文档训练 TODO 补充编号 ==========
ErrorCode FILE_TRAINING_NOT_EXISTS = new ErrorCode(1000001, "文档训练不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== GPT 专用的基础的配置 TODO 补充编号 ==========
ErrorCode GPT_CFG_NOT_EXISTS = new ErrorCode(1000001, "GPT 专用的基础的配置不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 终端平台即第三方平台配置 TODO 补充编号 ==========
ErrorCode THIRD_PARTY_CFG_NOT_EXISTS = new ErrorCode(1000001, "终端平台即第三方平台配置不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== 客户信息收集 TODO 补充编号 ==========
ErrorCode USER_INFO_COLLECTION_NOT_EXISTS = new ErrorCode(1000001, "客户信息收集不存在");

// TODO 待办：请将下面的错误码复制到 yudao-module-asc-api 模块的 ErrorCodeConstants 类中。注意，请给“TODO 补充编号”设置一个错误码编号！！！
// ========== WhatsApp 平台配置 TODO 补充编号 ==========
ErrorCode WHATSAPP_CFG_NOT_EXISTS = new ErrorCode(1000001, "WhatsApp 平台配置不存在");

}
