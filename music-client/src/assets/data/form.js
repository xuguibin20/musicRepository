import { Message } from "element-ui";

const rules = {
  Lusername: [{ required: true, trigger: "blur", message: "请输入账号" }],
  Lpassword: [{ required: true, trigger: "blur", message: "请输入密码" }],

  Rusername: [{ required: true, trigger: "blur", message: "请输入账号" }],
  Rpassword: [{ required: true, trigger: "blur", message: "请输入密码" }],
};

export { rules };
