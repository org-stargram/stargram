
	$('#btn_submit').click(function(e){
		e.preventDefault();
		$("#submit").click();
	});

	$('#camera_file').click(function(e){
		e.preventDefault();
		$("#files").click();
//		var ext = $("#c_file").val().split(".").pop().toLowerCase();
//		if(ext.length > 0){
//			if($.inArray(ext, ["gif","png","jpg","jpeg"]) == -1) {
//				alert("gif,png,jpg 파일만 업로드 할수 있습니다.");
//				return false;
//			}
//		}
//		$("#c_file").val().toLowerCase();
	});


	$(document).ready(function() {
		$("#files").on("change", handleImgsFilesSelect);
	});

	var sel_files = [];

	function handleImgsFilesSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
/*			if(!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
*/

			sel_files.push(f);

			var reader = new FileReader();
			reader.onload = function(e) {

			    var img_html = null;

				if(f.type.match("image.*")) {
			        img_html = "<img src=\"" + e.target.result + "\" />";
				}
				if(f.type.match("video.*")) {
                    img_html = "<video controls>";
                    img_html += "<source src=\"" + e.target.result + "\" />";
                    img_html += "</video>";
				}

				$(".c_file_imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
		});
	}

