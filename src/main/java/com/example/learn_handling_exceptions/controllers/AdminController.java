package com.example.learn_handling_exceptions.controllers;

import com.example.learn_handling_exceptions.Dtos.PeriodDTO;
import com.example.learn_handling_exceptions.Dtos.ResponseDTO;
import com.example.learn_handling_exceptions.Dtos.RoomDto;
import com.example.learn_handling_exceptions.Repositories.ReservationRepository;
import com.example.learn_handling_exceptions.Repositories.RoomRepository;
import com.example.learn_handling_exceptions.Services.FileService;
import com.example.learn_handling_exceptions.Services.RoomService;
import com.example.learn_handling_exceptions.entities.File;
import com.example.learn_handling_exceptions.entities.Room;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/secure")
public class AdminController {

    private final RoomRepository  roomRepository;
    private final JavaMailSender mailSender;
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;
    private final FileService fileService;
    private final String uploadDir = "upload/";

    public AdminController(ReservationRepository reservationRepository, RoomRepository roomRepository, JavaMailSender mailSender, RoomService roomService, FileService fileService) {
        this.roomRepository = roomRepository;
        this.mailSender = mailSender;
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
        this.fileService = fileService;
    }

    @GetMapping("/getAvailableRooms")
    public void getRooms(@RequestBody PeriodDTO period) {

      List<Integer> ids = reservationRepository.getReservationBetweenAPeriod(period.getDebut(), period.getFin());
      List<Integer> availableIds = reservationRepository.getAvailableRooms(ids);
     for (Integer id : availableIds){
         System.out.println(id);
     }
    }

    @PostMapping("/addroom")
    @Transactional(rollbackOn = ArithmeticException.class)
    public ResponseEntity<?> addRoom(@Valid @RequestBody Room room) {
        if(roomRepository.findByNumber(room.getNumber())==null){
            Room savedRoom = roomRepository.save(room);

            RoomDto ResponseRoom = roomService.RoomToRoomDto(savedRoom);
            return new ResponseEntity<>(Collections.singletonList(ResponseRoom) , HttpStatus.CREATED);
        }
        HashMap<String , String> hashMap = new HashMap<>();
        hashMap.put("message", "A room with this number already exists");
        return new ResponseEntity<>(Collections.singletonList(hashMap), HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> rooms(){
        List<Room> rooms = roomRepository.findAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/send-mail")
    public String Send(){
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("iliasna2004@gmail.com");
            message.setTo("iliasna2004@gmail.com");
            message.setSubject("test email");
            message.setText("this is just a test mail from me to me !!");

            mailSender.send(message);
            return "message is sent successefully!!";
        }catch(Exception ex){
            return ex.getMessage();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile[] file) {
       try{

           for(MultipartFile multipartFile : file){
               Path path = Paths.get(uploadDir + multipartFile.getOriginalFilename());
               Files.createDirectories(path.getParent());
               Files.write(path, multipartFile.getBytes());
           }

           return new ResponseEntity<>("file uploaded successefully",HttpStatus.CREATED);
       }catch(Exception ex){
             return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping("/files/{id}")
    public ResponseEntity<?> getFile(@PathVariable Integer id) {
        // Fetch the file from the database using the ID
        Optional<File> file = fileService.getFileById(id);

        if (file == null) {
            return ResponseEntity.notFound().build(); // Return 404 if file not found
        }

        // Set the correct content type (e.g., image/jpeg, application/pdf)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(file.get().getType()));
        headers.setContentDispositionFormData("attachment", file.get().getFileName());  // Optional, for downloading the file


        // Return the file data as a byte array in the response
        return new ResponseEntity<>(file.get().getFileData(),headers, HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<?> getFile() {
        try {
            Path filePath = Paths.get("upload/").resolve("ILIAS_NAOUI_CV.pdf").normalize();
            UrlResource resource = new UrlResource(filePath.toUri());

            System.out.println(resource);

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath); // Get file type

            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getRooms")
    public List<Room> getRooms(){
        return roomService.searchRooms(150F);
    }


}
