package com.greatminds.ayni.iam.interfaces.rest;

import com.greatminds.ayni.iam.domain.model.queries.GetUserByIdQuery;
import com.greatminds.ayni.iam.domain.services.UserCommandService;
import com.greatminds.ayni.iam.domain.services.UserQueryService;
import com.greatminds.ayni.iam.interfaces.rest.resources.SignInResource;
import com.greatminds.ayni.iam.interfaces.rest.resources.SignUpResource;
import com.greatminds.ayni.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.greatminds.ayni.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.greatminds.ayni.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.greatminds.ayni.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "Authentication", description = "Authentication endpoints")
@RequestMapping(value ="/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public AuthController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInResource resource){
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) return ResponseEntity.notFound().build();

        var authenticatedResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return new ResponseEntity<>(authenticatedResource, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpResource resource){
        var signInCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        var userId = userCommandService.handle(signInCommand);
        if (userId == 0L){
            return ResponseEntity.badRequest().build();
        }

        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
