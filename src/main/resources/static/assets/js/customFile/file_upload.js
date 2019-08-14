	$('#camera_file').click(function(e){
		e.preventDefault();
		$("#c_file").click();
		var ext = $("#c_file").val().split(".").pop().toLowerCase();
		if(ext.length > 0){
			if($.inArray(ext, ["gif","png","jpg","jpeg"]) == -1) {
				alert("gif,png,jpg 파일만 업로드 할수 있습니다.");
				return false;
			}
		}
		$("#c_file").val().toLowerCase();
	});

	$('#video_file').click(function(e){
		alert("video_file");
		e.preventDefault();
		$("#v_file").click();
		var ext = $("#v_file").val().split(".").pop().toLowerCase();
		if(ext.length > 0){
			if($.inArray(ext, ["avi","mp4","mkv","wmv", "ts", "tp", "mov", "3gp"]) == -1) {
				alert("avi,mp4,mkv,wmv,3gp,mov,tp,ts 파일만 업로드 할수 있습니다.");
				return false;
			}
		}
		$("#v_file").val().toLowerCase();
	});


	$(document).ready(function() {
		$("#c_file").on("change", handleImgsFilesSelect);
		$("#v_file").on("change", handleVideosFilesSelect);
	});

	var sel_files = [];

	function handleImgsFilesSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if(!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_files.push(f);

			var reader = new FileReader();
			reader.onload = function(e) {
				var img_html = "<img src=\"" + e.target.result + "\" />";
				$(".c_file_imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
		});
	}


	var sel_videos_files = [];

	function handleVideosFilesSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if(!f.type.match("video.*")) {
				alert("확장자는 비디오 확장자만 가능합니다.");
				return;
			}

			sel_videos_files.push(f);

			var reader = new FileReader();
			reader.onload = function(e) {
				var img_html = "<video controls>";
				img_html += "<source src=\"" + e.target.result + "\" />";
				img_html += "</video>";
				$(".c_file_imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
		});
	}
