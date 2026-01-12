//js
        function insert(num) {
            document.form.textView.value = document.form.textView.value + num;
        }

        function equal() {
            let exp = document.form.textView.value;
            if (exp) {
                let eval1 = eval(document.form.textView.value);
                    document.form.textView.value=eval1;
                }
            
        }

        function Mclean() {
            // TODO 清理输入框的文字
            document.form.textView.value = null;
        }

        function back() {
            // TODO 删除文本框的一个字符
            let exp = document.form.textView.value;
            document.form.textView.value = exp.substring(0, exp.length - 1);
            // 截取字符串
        }
    
     